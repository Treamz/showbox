package com.treamz.showbox.presentation.screens.show_details

import com.treamz.showbox.domain.models.show_details.ShowDetails

data class ShowDetailsState(
    val isLoading: Boolean = false,
    val showDetails: ShowDetails? = null,
    val error: String = ""
)