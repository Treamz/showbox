package com.treamz.showbox.presentation.screens.home_screen.components.top_rated_shows_row

import androidx.paging.PagingData
import com.treamz.showbox.data.remote.dto.movie_disover.ResultDto
import com.treamz.showbox.data.remote.dto.tv_list.TvResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class TopRatedShowsRowState(val topRatedMovies: Flow<PagingData<TvResult>> = emptyFlow())