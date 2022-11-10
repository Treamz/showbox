package com.treamz.showbox.domain.repository

import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import com.treamz.showbox.common.Resource
import com.treamz.showbox.data.remote.TheMovieDBApi
import com.treamz.showbox.data.remote.dto.guest_session.TMDBGuestSessionDto
import com.treamz.showbox.domain.models.Response

typealias OneTapSignInResponse = Response<BeginSignInResult>
typealias SignInWithGoogleResponse = Response<Boolean>

interface AuthRepository {
    val isUserAuthenticatedInFirebase: Boolean

    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signup(name: String, email: String, password: String): Resource<FirebaseUser>
    fun logout()

    suspend fun oneTapSignInWithGoogle(): OneTapSignInResponse

    suspend fun generateTheMovieDBSession() : TMDBGuestSessionDto

    suspend fun firebaseSignInWithGoogle(googleCredential: AuthCredential): SignInWithGoogleResponse


}
