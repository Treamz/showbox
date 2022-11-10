package com.treamz.showbox.data.remote.dto.guest_session

data class TMDBGuestSessionDto(
    val expires_at: String = "",
    val guest_session_id: String = "",
    val success: Boolean = false
)