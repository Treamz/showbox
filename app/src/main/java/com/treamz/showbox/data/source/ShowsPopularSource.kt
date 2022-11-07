package com.treamz.showbox.data.source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.treamz.showbox.data.remote.dto.tv_list.TvResult
import com.treamz.showbox.domain.repository.TvRepository
import javax.inject.Inject

class ShowsPopularSource @Inject constructor(
    private val tvRepository: TvRepository
) : PagingSource<Int, TvResult>() {
    override fun getRefreshKey(state: PagingState<Int, TvResult>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvResult> {
        return try {
            val nextPage = params.key ?: 1
            val movieDiscover = tvRepository.getPopularShows(
                "ru_RU",
                page = nextPage
            ).results
            LoadResult.Page(
                data = movieDiscover,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage.plus(1)
            )

        } catch (ex: Exception) {
            Log.d("SHOWBOXAPPP", ex.localizedMessage)
            LoadResult.Error(ex)
        }
    }

}