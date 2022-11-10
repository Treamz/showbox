package com.treamz.showbox.presentation.screens.home_screen

import androidx.paging.PagingData
import com.treamz.showbox.data.remote.dto.movie_disover.ResultDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeTabState(val popularMovies: Flow<PagingData<ResultDto>> = emptyFlow())