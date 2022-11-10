package com.treamz.showbox.presentation.screens.movie_discover

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.treamz.showbox.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.treamz.showbox.data.remote.dto.movie_disover.ResultDto
import com.treamz.showbox.data.source.MovieDiscoverSource
import kotlinx.coroutines.flow.Flow


@HiltViewModel
class MovieDiscoverViewModel @Inject constructor(
    movieRepository: MovieRepository
) : ViewModel() {
    private val _state = mutableStateOf(MovieDiscoverState())
    val state: State<MovieDiscoverState> = _state

    init {
        val movieDiscover: Flow<PagingData<ResultDto>> = Pager(PagingConfig(1)) {
            MovieDiscoverSource(
                movieRepository = movieRepository
            )
        }.flow.cachedIn(viewModelScope)
        _state.value = MovieDiscoverState(movieDiscover = movieDiscover)
    }
}