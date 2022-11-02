package com.treamz.showbox.presentation.home_screen.components.top_rated_movie_row

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.treamz.showbox.data.remote.dto.movie_disover.ResultDto
import com.treamz.showbox.data.source.MoviePopularSource
import com.treamz.showbox.data.source.MovieTopRatedSource
import com.treamz.showbox.domain.repository.MovieRepository
import com.treamz.showbox.presentation.home_screen.HomeTabState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class TopRatedMovieRowViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _state = mutableStateOf(TopRatedMovieRowState())
    val state: State<TopRatedMovieRowState> = _state

    init {
        val topRatedMovie: Flow<PagingData<ResultDto>> = Pager(PagingConfig(1)) {
            MovieTopRatedSource(
                movieRepository = movieRepository
            )
        }.flow.cachedIn(viewModelScope)
        _state.value = TopRatedMovieRowState(topRatedMovie)
    }
}