package com.treamz.showbox.data.repository

import com.treamz.showbox.common.Constants
import com.treamz.showbox.data.remote.TheMovieDBApi
import com.treamz.showbox.data.remote.dto.movie_details.MovieDetailsDto
import com.treamz.showbox.data.remote.dto.movie_disover.MovieListDto
import com.treamz.showbox.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val theMovieDBApi: TheMovieDBApi
) : MovieRepository {
    override suspend fun getMovieDiscover(
        language: String,
        sort_by: String,
        include_adult: Boolean,
        include_video: Boolean,
        with_watch_monetization_types: Boolean,
        page: Int
    ): MovieListDto {
        return theMovieDBApi.getDiscover(
            apiKey = Constants.TMDB_API_KEY,
            language = language,
            sort_by = sort_by,
            include_adult = include_adult,
            include_video = include_video,
            with_watch_monetization_types = with_watch_monetization_types,
            page = page
        )
    }

    override suspend fun getMoviePopular(language: String, page: Int): MovieListDto {
        return theMovieDBApi.getPopularMovies(
            apiKey = Constants.TMDB_API_KEY,
            page = page,
            language = language
        )
    }

    override suspend fun getMovieTopRated(language: String, page: Int): MovieListDto {
        return theMovieDBApi.getTopRatedMovies(
            apiKey = Constants.TMDB_API_KEY,
            page = page,
            language = language
        )
    }

    override suspend fun getMovieDetails(movieId: String, language: String): MovieDetailsDto {
        return theMovieDBApi.getMovieDetails(
            apiKey = Constants.TMDB_API_KEY,
            language = language,
            movieId = movieId
        )
    }
}