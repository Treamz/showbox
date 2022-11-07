package com.treamz.showbox.domain.use_case.get_show_details

import android.util.Log
import com.treamz.showbox.common.Resource
import com.treamz.showbox.data.remote.dto.movie_details.toMovieDetails
import com.treamz.showbox.data.remote.dto.tv_details.ShowDetailsDto
import com.treamz.showbox.data.remote.dto.tv_details.toShowDetails
import com.treamz.showbox.domain.models.movie_details.MovieDetails
import com.treamz.showbox.domain.models.show_details.ShowDetails
import com.treamz.showbox.domain.repository.TvRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetShowDetails @Inject constructor(
    private val tvRepository: TvRepository
) {
    operator fun invoke(movieId: String): Flow<Resource<ShowDetails>> = flow {
        Log.d("SHOWBOXAPP", "${movieId}")

        try {
            emit(Resource.Loading())
            val movieDetails = tvRepository.getShowsDetails(movieId = movieId, language = "ru_RU").toShowDetails()
            Log.d("SHOWBOXAPP", "${movieDetails.name}")
            emit(Resource.Success<ShowDetails>(movieDetails))
        } catch (ex: Exception) {
            Log.d("SHOWBOXAPP", "${ex.localizedMessage}")

            emit(Resource.Error(ex.localizedMessage))
        }
    }
}