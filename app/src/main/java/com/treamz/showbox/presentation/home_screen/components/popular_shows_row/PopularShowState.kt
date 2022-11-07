package com.treamz.showbox.presentation.home_screen.components.popular_shows_row

import androidx.paging.PagingData
import com.treamz.showbox.data.remote.dto.tv_list.TvResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class PopularShowState(val popularShows: Flow<PagingData<TvResult>> = emptyFlow())