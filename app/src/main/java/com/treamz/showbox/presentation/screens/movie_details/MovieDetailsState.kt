package com.treamz.showbox.presentation.screens.movie_details

import com.treamz.showbox.domain.models.movie_details.MovieDetails

data class MovieDetailsState(
    val isLoading: Boolean = false,
    val movieDetails: MovieDetails? = null,
    val error: String = ""
)