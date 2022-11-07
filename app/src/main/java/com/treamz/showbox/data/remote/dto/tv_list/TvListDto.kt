package com.treamz.showbox.data.remote.dto.tv_list

data class TvListDto(
    val page: Int,
    val results: List<TvResult>,
    val total_pages: Int,
    val total_results: Int
)