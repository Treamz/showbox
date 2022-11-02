package com.treamz.showbox.data.remote.dto.movie_disover

import com.treamz.showbox.domain.models.movie_discover.MovieDiscover

data class MovieListDto(
    val page: Int,
    val results: List<ResultDto>,
    val total_pages: Int,
    val total_results: Int
)

fun MovieListDto.toMovieDiscover(): MovieDiscover {
    return MovieDiscover(
        page = page,
        results = results,
        total_pages = total_pages,
        total_results = total_results
    )
}

