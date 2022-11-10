package com.treamz.showbox.presentation.screens.search_screen

import com.treamz.showbox.domain.models.multi_search.MultiSearch

data class SearchScreenState(
    val multiSearchdata: MultiSearch? = null,
    val searchQuery: String = "Adam",
    val error: String = "",
    val isLoading: Boolean = false
)
