package com.treamz.showbox.domain.use_case.get_movie_details

import android.util.Log
import com.treamz.showbox.common.Resource
import com.treamz.showbox.data.remote.dto.movie_details.toMovieDetails
import com.treamz.showbox.domain.models.movie_details.MovieDetails
import com.treamz.showbox.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieDetails @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(movieId: String): Flow<Resource<MovieDetails>> = flow {
        Log.d("SHOWBOXAPP", "${movieId}")

        try {
            emit(Resource.Loading())
            val movieDetails = movieRepository.getMovieDetails(movieId = movieId, language = "ru_RU").toMovieDetails()
            Log.d("SHOWBOXAPP", "${movieDetails.title}")
            emit(Resource.Success<MovieDetails>(movieDetails))
        } catch (ex: Exception) {
            Log.d("SHOWBOXAPP", "${ex.localizedMessage}")

            emit(Resource.Error(ex.localizedMessage))
        }
    }
}