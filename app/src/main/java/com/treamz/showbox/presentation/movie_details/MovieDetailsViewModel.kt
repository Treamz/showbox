package com.treamz.showbox.presentation.movie_details

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.treamz.showbox.common.Constants
import com.treamz.showbox.common.Resource
import com.treamz.showbox.domain.repository.MovieRepository
import com.treamz.showbox.domain.use_case.get_movie_details.GetMovieDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetails: GetMovieDetails,
    savedStateHandle: SavedStateHandle

) : ViewModel() {
    private val _state = mutableStateOf(MovieDetailsState())
    val state: State<MovieDetailsState> = _state

    init {

        savedStateHandle.get<String>(Constants.PARAM_MOVIE_ID)?.let { movieId ->
            getMovieDetails(movieId = movieId).onEach {
                Log.d("SHOWBOXAPPP",it.toString())
                when (it) {
                    is Resource.Success -> {
                        _state.value = MovieDetailsState(movieDetails = it.data)
                    }
                    is Resource.Error -> {
                        _state.value =
                            MovieDetailsState(error = it.message ?: "An unxpected error occured")
                    }
                    is Resource.Loading -> {
                        _state.value =
                            MovieDetailsState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }

    }
}