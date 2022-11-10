package com.treamz.showbox.domain.repository

import com.treamz.showbox.data.remote.dto.guest_session.TMDBGuestSessionDto
import com.treamz.showbox.domain.models.Response

typealias SignOutResponse = Response<Boolean>
typealias RevokeAccessResponse = Response<Boolean>

interface ProfileRepository {
    val displayName: String
    val photoUrl: String

    suspend fun signOut(): SignOutResponse

    suspend fun revokeAccess(): RevokeAccessResponse

    suspend fun getGuestSessionTMDB(): TMDBGuestSessionDto?

}