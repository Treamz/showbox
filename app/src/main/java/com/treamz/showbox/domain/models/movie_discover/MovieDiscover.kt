package com.treamz.showbox.domain.models.movie_discover

import com.treamz.showbox.data.remote.dto.movie_disover.ResultDto


data class MovieDiscover(
    val page: Int,
    val results: List<ResultDto>,
    val total_pages: Int,
    val total_results: Int
)
