package com.treamz.showbox.presentation.screens.home_screen.components.top_rated_movie_row

import androidx.paging.PagingData
import com.treamz.showbox.data.remote.dto.movie_disover.ResultDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class TopRatedMovieRowState(val topRatedMovies: Flow<PagingData<ResultDto>> = emptyFlow())