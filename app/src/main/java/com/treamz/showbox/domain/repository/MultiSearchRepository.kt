package com.treamz.showbox.domain.repository

import com.treamz.showbox.data.remote.dto.multi_search.MultiSearchDto

interface MultiSearchRepository {
    suspend fun getMultiSearch(
        query: String,
        include_adult: Boolean
    ) : MultiSearchDto
}