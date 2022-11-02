package com.treamz.showbox.domain.use_case.get_movie_discover

import com.treamz.showbox.common.Resource
import com.treamz.showbox.domain.models.movie_discover.MovieDiscover
import com.treamz.showbox.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieDiscover @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Flow<Resource<MovieDiscover>> = flow {
//        try {
//            emit(Resource.Loading())
//            val movieDiscover =
//                movieRepository.getMovieDiscover(
//                    "ru_RU",
//                    "popularity.desc",
//                    include_adult = true,
//                    include_video = true,
//                    with_watch_monetization_types = true
//                )
//        } catch (e: HttpException) {
//
//        } catch (e: IOException) {
//
//        }
    }
}