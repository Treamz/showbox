package com.treamz.showbox.data.repository

import com.treamz.showbox.common.Constants
import com.treamz.showbox.data.preferences.PrefRepository
import com.treamz.showbox.data.remote.TheMovieDBApi
import com.treamz.showbox.data.remote.dto.movie_details.MovieDetailsDto
import com.treamz.showbox.data.remote.dto.movie_disover.MovieListDto
import com.treamz.showbox.data.remote.dto.tv_details.ShowDetailsDto
import com.treamz.showbox.data.remote.dto.tv_list.TvListDto
import com.treamz.showbox.domain.repository.MovieRepository
import com.treamz.showbox.domain.repository.TvRepository
import javax.inject.Inject

class TvRepositoryImpl @Inject constructor(
    private val theMovieDBApi: TheMovieDBApi,
    private val prefRepository: PrefRepository

) : TvRepository {
    override suspend fun getShowsDiscover(
        language: String,
        sort_by: String,
        include_adult: Boolean,
        include_video: Boolean,
        with_watch_monetization_types: Boolean,
        page: Int
    ): TvListDto {
        return theMovieDBApi.getDiscoverShows(
            apiKey = Constants.TMDB_API_KEY,
            language = prefRepository.getLanguage().code,
            sort_by = sort_by,
            include_adult = include_adult,
            include_video = include_video,
            with_watch_monetization_types = with_watch_monetization_types,
            page = page
        )
    }

    override suspend fun getPopularShows(language: String, page: Int): TvListDto {
        return theMovieDBApi.getPopularShows(
            apiKey = Constants.TMDB_API_KEY,
            page = page,
            language = prefRepository.getLanguage().code,
        )
    }

    override suspend fun getTopRatedShows(language: String, page: Int): TvListDto {
        return theMovieDBApi.getTopRatedShows(
            apiKey = Constants.TMDB_API_KEY,
            page = page,
            language = prefRepository.getLanguage().code,
        )
    }

    override suspend fun getShowsDetails(movieId: String, language: String): ShowDetailsDto {
        return theMovieDBApi.getShowDetails(
            apiKey = Constants.TMDB_API_KEY,
            language = prefRepository.getLanguage().code,
            movieId = movieId
        )
    }
}