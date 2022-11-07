package com.treamz.showbox.presentation.home_screen.components.popular_shows_row

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.treamz.showbox.data.remote.dto.tv_list.TvResult
import com.treamz.showbox.data.source.ShowsPopularSource
import com.treamz.showbox.domain.repository.TvRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class PopularShowsRowViewModel @Inject constructor(
    private val tvRepository: TvRepository
) : ViewModel() {

    private val _state = mutableStateOf(PopularShowState())
    val state: State<PopularShowState> = _state

    init {
        val popularMovie: Flow<PagingData<TvResult>> = Pager(PagingConfig(1)) {
            ShowsPopularSource(
                tvRepository = tvRepository
            )
        }.flow.cachedIn(viewModelScope)
        _state.value = PopularShowState(popularMovie)
    }
}