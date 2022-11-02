package com.treamz.showbox.data.remote

import com.treamz.showbox.data.remote.dto.movie_details.MovieDetailsDto
import com.treamz.showbox.data.remote.dto.movie_disover.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBApi {

    @GET("/3/discover/movie")
    suspend fun getDiscover(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("sort_by") sort_by: String,
        @Query("include_adult") include_adult: Boolean,
        @Query("include_video") include_video: Boolean,
        @Query("with_watch_monetization_types") with_watch_monetization_types: Boolean,
        @Query("page") page: Int
    ): MovieListDto

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): MovieListDto

    @GET("/3/movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): MovieListDto

    @GET("/3/movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        ): MovieDetailsDto
}