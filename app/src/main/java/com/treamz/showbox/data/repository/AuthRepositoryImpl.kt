package com.treamz.showbox.data.repository

import android.util.Log
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.treamz.showbox.common.Constants
import com.treamz.showbox.common.Constants.SIGN_IN_REQUEST
import com.treamz.showbox.common.Constants.SIGN_UP_REQUEST
import com.treamz.showbox.common.Constants.USERS
import com.treamz.showbox.common.Constants.USERS_SESSIONS

import com.treamz.showbox.common.Resource
import com.treamz.showbox.data.remote.TheMovieDBApi
import com.treamz.showbox.data.remote.dto.guest_session.TMDBGuestSessionDto
import com.treamz.showbox.domain.models.Response

import com.treamz.showbox.domain.repository.AuthRepository
import com.treamz.showbox.domain.repository.OneTapSignInResponse
import com.treamz.showbox.domain.repository.SignInWithGoogleResponse

import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named


class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private var oneTapClient: SignInClient,
    @Named(SIGN_IN_REQUEST)
    private var signInRequest: BeginSignInRequest,
    @Named(SIGN_UP_REQUEST)
    private var signUpRequest: BeginSignInRequest,
    private val db: FirebaseFirestore,
    private  val theMovieDBApi: TheMovieDBApi
) : AuthRepository {
    override val isUserAuthenticatedInFirebase = firebaseAuth.currentUser != null

    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun login(email: String, password: String): Resource<FirebaseUser> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Resource.Success(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.localizedMessage)
        }
    }

    override suspend fun signup(name: String, email: String, password: String): Resource<FirebaseUser> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            result.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(name).build())?.await()
            return Resource.Success(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.localizedMessage)
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }

    override suspend fun oneTapSignInWithGoogle(): OneTapSignInResponse {
        return try {
            val signInResult = oneTapClient.beginSignIn(signInRequest).await()
            Log.d("SHOWBOXAPP",signInResult.toString());
            Response.Success(signInResult)

        } catch (e: Exception) {
            Log.d("SHOWBOXAPP",e.localizedMessage);

            try {
                val signUpResult = oneTapClient.beginSignIn(signUpRequest).await()
                Response.Success(signUpResult)
            } catch (e: Exception) {
                Response.Failure(e)
            }
        }
    }

    override suspend fun generateTheMovieDBSession(): TMDBGuestSessionDto {
        return theMovieDBApi.generateGuestSession(
            apiKey = Constants.TMDB_API_KEY,
        )
    }

    override suspend fun firebaseSignInWithGoogle(
        googleCredential: AuthCredential
    ): SignInWithGoogleResponse {
        return try {
            val authResult = firebaseAuth.signInWithCredential(googleCredential).await()
            val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false
            if (true) {
                addUserToFirestore()
            }
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    private suspend fun addUserToFirestore() {
        firebaseAuth.currentUser?.apply {
            val data = db.collection(USERS_SESSIONS).document(uid).get().await()
            Log.d("SHOWBOXAPP NEW SESSION",data.toString())

            if(!data.exists()) {
                Log.d("SHOWBOXAPP NEW SESSION","DATA NOT EXIST")

                val res = generateTheMovieDBSession()
                Log.d("SHOWBOXAPP NEW SESSION",res.toString())
                db.collection(USERS_SESSIONS).document(uid).set(res).await()

            }
        }
    }
}
