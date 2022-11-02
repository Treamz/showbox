package com.treamz.showbox.presentation.home_screen.components.popular_movies_row

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
import com.treamz.showbox.domain.repository.MovieRepository
import com.treamz.showbox.presentation.home_screen.HomeTabState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class PopularMovieRowViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _state = mutableStateOf(HomeTabState())
    val state: State<HomeTabState> = _state

    init {
        val popularMovie: Flow<PagingData<ResultDto>> = Pager(PagingConfig(1)) {
            MoviePopularSource(
                movieRepository = movieRepository
            )
        }.flow.cachedIn(viewModelScope)
        _state.value = HomeTabState(popularMovie)
    }
}