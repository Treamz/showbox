package com.treamz.showbox.data.repository

import com.treamz.showbox.common.Constants
import com.treamz.showbox.data.preferences.PrefRepository
import com.treamz.showbox.data.remote.TheMovieDBApi
import com.treamz.showbox.data.remote.dto.multi_search.MultiSearchDto
import com.treamz.showbox.domain.repository.MultiSearchRepository
import javax.inject.Inject

class MultiSearchRepositoryImpl @Inject constructor(
    private val theMovieDBApi: TheMovieDBApi,
    private val prefRepository: PrefRepository

) : MultiSearchRepository {
    override suspend fun getMultiSearch(
        query: String,
        include_adult: Boolean
    ): MultiSearchDto {
        return theMovieDBApi.getMultiSearch(
            apiKey = Constants.TMDB_API_KEY,
            query = query,
            include_adult = include_adult,
            language = prefRepository.getLanguage().code
        )
    }


}