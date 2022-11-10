package com.treamz.showbox.presentation.screens.movie_discover

import androidx.paging.PagingData
import com.treamz.showbox.data.remote.dto.movie_disover.ResultDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MovieDiscoverState(
    val movieDiscover: Flow<PagingData<ResultDto>> = emptyFlow()
)