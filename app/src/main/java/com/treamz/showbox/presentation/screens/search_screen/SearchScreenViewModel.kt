package com.treamz.showbox.presentation.screens.search_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.treamz.showbox.common.Resource
import com.treamz.showbox.data.remote.dto.multi_search.toMultiSearch
import com.treamz.showbox.domain.models.multi_search.MultiSearch
import com.treamz.showbox.domain.repository.MultiSearchRepository
import com.treamz.showbox.presentation.screens.show_details.ShowDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val multiSearchRepository: MultiSearchRepository
) : ViewModel() {
    private val _state = mutableStateOf(SearchScreenState())
    val state: State<SearchScreenState> = _state

    init {
        sendQuery("Adam")
    }

    fun sendQuery(query: String) {
        Log.d("SHOWBOXAPP", "${query}")
        getSearch(query).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = SearchScreenState(multiSearchdata = it.data)
                }
                is Resource.Error -> {
                    _state.value =
                        SearchScreenState(error = it.message ?: "An unxpected error occured")
                }
                is Resource.Loading -> {
                    _state.value =
                        SearchScreenState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }
    fun getSearch(query:String): Flow<Resource<MultiSearch>> = flow {
        Log.d("SHOWBOXAPP", "${query}")
        try {
            emit(Resource.Loading())
            val searchDetails =
                multiSearchRepository.getMultiSearch(query = query, include_adult = true)
                    .toMultiSearch()
            Log.d("SHOWBOXAPP", "${searchDetails.results.get(1).name}")
            emit(Resource.Success<MultiSearch>(searchDetails))
        } catch (ex: Exception) {
            Log.d("SHOWBOXAPP", "${ex.localizedMessage}")

            emit(Resource.Error(ex.localizedMessage))
        }
    }
}