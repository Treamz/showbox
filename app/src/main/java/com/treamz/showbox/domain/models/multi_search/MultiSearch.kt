package com.treamz.showbox.domain.models.multi_search

import com.treamz.showbox.data.remote.dto.multi_search.SearchResult

data class MultiSearch(
    val page: Int,
    val results: List<SearchResult>,
    val total_pages: Int,
    val total_results: Int
)