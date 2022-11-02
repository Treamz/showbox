package com.treamz.showbox.data.source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.treamz.showbox.data.remote.dto.movie_disover.ResultDto
import com.treamz.showbox.domain.models.movie_discover.Result
import com.treamz.showbox.domain.repository.MovieRepository
import javax.inject.Inject

class MovieTopRatedSource @Inject constructor(
    private val movieRepository: MovieRepository
) : PagingSource<Int, ResultDto>() {
    override fun getRefreshKey(state: PagingState<Int, ResultDto>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultDto> {
        return try {
            val nextPage = params.key ?: 1
            val movieDiscover = movieRepository.getMovieTopRated(
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