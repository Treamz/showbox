package com.treamz.showbox.domain.repository

import com.treamz.showbox.data.remote.dto.movie_details.MovieDetailsDto
import com.treamz.showbox.data.remote.dto.movie_disover.MovieListDto
import com.treamz.showbox.data.remote.dto.tv_details.ShowDetailsDto
import com.treamz.showbox.data.remote.dto.tv_list.TvListDto

interface TvRepository {
    suspend fun getShowsDiscover(
        language: String,
        sort_by: String,
        include_adult: Boolean,
        include_video: Boolean,
        with_watch_monetization_types: Boolean,
        page: Int
    ): TvListDto

    suspend fun getPopularShows(
        language: String,
        page: Int
    ): TvListDto

    suspend fun getTopRatedShows(
        language: String,
        page: Int
    ): TvListDto

    suspend fun getShowsDetails(
        movieId: String,
        language: String
    ): ShowDetailsDto
}