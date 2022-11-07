package com.treamz.showbox.data.repository

import android.util.Log
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.treamz.showbox.common.Constants.SIGN_IN_REQUEST
import com.treamz.showbox.common.Constants.SIGN_UP_REQUEST
import com.treamz.showbox.common.Constants.USERS
import com.treamz.showbox.domain.models.Response
import com.treamz.showbox.domain.repository.AuthRepository
import com.treamz.showbox.domain.repository.OneTapSignInResponse
import com.treamz.showbox.domain.repository.SignInWithGoogleResponse
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl  @Inject constructor(
    private val auth: FirebaseAuth,
    private var oneTapClient: SignInClient,
    @Named(SIGN_IN_REQUEST)
    private var signInRequest: BeginSignInRequest,
    @Named(SIGN_UP_REQUEST)
    private var signUpRequest: BeginSignInRequest,
) : AuthRepository {
    override val isUserAuthenticatedInFirebase = auth.currentUser != null

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

    override suspend fun firebaseSignInWithGoogle(
        googleCredential: AuthCredential
    ): SignInWithGoogleResponse {
        return try {
            val authResult = auth.signInWithCredential(googleCredential).await()
            val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false
//            if (isNewUser) {
//                addUserToFirestore()
//            }
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

//    private suspend fun addUserToFirestore() {
//        auth.currentUser?.apply {
//            val user = toUser()
//            db.collection(USERS).document(uid).set(user).await()
//        }
//    }
}