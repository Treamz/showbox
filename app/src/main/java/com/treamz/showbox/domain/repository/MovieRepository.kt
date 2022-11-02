package com.treamz.showbox.domain.repository

import com.treamz.showbox.data.remote.dto.movie_details.MovieDetailsDto
import com.treamz.showbox.data.remote.dto.movie_disover.MovieListDto

interface MovieRepository {
    suspend fun getMovieDiscover(
        language: String,
        sort_by: String,
        include_adult: Boolean,
        include_video: Boolean,
        with_watch_monetization_types: Boolean,
        page: Int
    ): MovieListDto

    suspend fun getMoviePopular(
        language: String,
        page: Int
    ): MovieListDto

    suspend fun getMovieTopRated(
        language: String,
        page: Int
    ): MovieListDto

    suspend fun getMovieDetails(
        movieId: String,
        language: String
    ): MovieDetailsDto
}