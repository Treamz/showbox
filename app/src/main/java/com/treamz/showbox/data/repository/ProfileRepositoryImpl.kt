package com.treamz.showbox.data.repository

import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.treamz.showbox.common.Constants
import com.treamz.showbox.data.remote.dto.guest_session.TMDBGuestSessionDto
import com.treamz.showbox.domain.models.Response
import com.treamz.showbox.domain.repository.ProfileRepository
import com.treamz.showbox.domain.repository.RevokeAccessResponse
import com.treamz.showbox.domain.repository.SignOutResponse
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private var oneTapClient: SignInClient,
    private var signInClient: GoogleSignInClient,
    private val db: FirebaseFirestore
) : ProfileRepository {
    override val displayName = firebaseAuth.currentUser?.displayName.toString()
    override val photoUrl = firebaseAuth.currentUser?.photoUrl.toString()

    override suspend fun signOut(): SignOutResponse {
        return try {
            oneTapClient.signOut().await()
            firebaseAuth.signOut()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun revokeAccess(): RevokeAccessResponse {
        return try {
            firebaseAuth.currentUser?.apply {
//                db.collection(USERS).document(uid).delete().await()
                signInClient.revokeAccess().await()
                oneTapClient.signOut().await()
                delete().await()
            }
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun getGuestSessionTMDB(): TMDBGuestSessionDto? {
        try {
            val uid = firebaseAuth.currentUser?.uid
            val guestSession = db.collection(Constants.USERS_SESSIONS).document(uid?:"").get().await()
            val result = guestSession.toObject<TMDBGuestSessionDto>()
            return result
        }
        catch (e:Exception) {
            return  TMDBGuestSessionDto()
        }
    }
}