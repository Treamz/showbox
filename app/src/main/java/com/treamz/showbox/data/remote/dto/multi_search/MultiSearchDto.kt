package com.treamz.showbox.data.remote.dto.multi_search

import com.treamz.showbox.domain.models.multi_search.MultiSearch

data class MultiSearchDto(
    val page: Int,
    val results: List<SearchResult>,
    val total_pages: Int,
    val total_results: Int
)


fun MultiSearchDto.toMultiSearch(): MultiSearch {
    return MultiSearch(
        page = page,
        results = results,
        total_pages = total_pages,
        total_results = total_results
    )
}