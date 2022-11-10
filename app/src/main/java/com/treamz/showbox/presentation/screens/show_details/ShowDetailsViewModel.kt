package com.treamz.showbox.presentation.screens.show_details

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.treamz.showbox.common.Constants
import com.treamz.showbox.common.Resource
import com.treamz.showbox.domain.use_case.get_show_details.GetShowDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ShowDetailsViewModel @Inject constructor(
    private val getShowDetails: GetShowDetails,
    savedStateHandle: SavedStateHandle

) : ViewModel() {
    private val _state = mutableStateOf(ShowDetailsState())
    val state: State<ShowDetailsState> = _state

    init {

        savedStateHandle.get<String>(Constants.PARAM_MOVIE_ID)?.let { movieId ->
            getShowDetails(movieId = movieId).onEach {
                Log.d("SHOWBOXAPPP",it.toString())
                when (it) {
                    is Resource.Success -> {
                        _state.value = ShowDetailsState(showDetails = it.data)
                    }
                    is Resource.Error -> {
                        _state.value =
                            ShowDetailsState(error = it.message ?: "An unxpected error occured")
                    }
                    is Resource.Loading -> {
                        _state.value =
                            ShowDetailsState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }

    }
}