package com.treamz.showbox.presentation.screens.home_screen.components.top_rated_shows_row

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.treamz.showbox.data.remote.dto.movie_disover.ResultDto
import com.treamz.showbox.data.remote.dto.tv_list.TvResult
import com.treamz.showbox.data.source.MoviePopularSource
import com.treamz.showbox.data.source.MovieTopRatedSource
import com.treamz.showbox.data.source.ShowsTopRatedSource
import com.treamz.showbox.domain.repository.MovieRepository
import com.treamz.showbox.domain.repository.TvRepository
import com.treamz.showbox.presentation.screens.home_screen.HomeTabState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class TopRatedShowsRowViewModel @Inject constructor(
    private val tvRepository: TvRepository
) : ViewModel() {

    private val _state = mutableStateOf(TopRatedShowsRowState())
    val state: State<TopRatedShowsRowState> = _state

    init {
        val topRatedMovie: Flow<PagingData<TvResult>> = Pager(PagingConfig(1)) {
            ShowsTopRatedSource(
                tvRepository = tvRepository
            )
        }.flow.cachedIn(viewModelScope)
        _state.value = TopRatedShowsRowState(topRatedMovie)
    }
}