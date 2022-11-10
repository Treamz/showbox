package com.treamz.showbox.presentation.screens.profile_screen

import androidx.paging.PagingData
import com.treamz.showbox.data.remote.dto.guest_session.TMDBGuestSessionDto
import com.treamz.showbox.data.remote.dto.movie_disover.ResultDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class TmdbSessionState(val tmdbGuestSessionDto: TMDBGuestSessionDto? = null)