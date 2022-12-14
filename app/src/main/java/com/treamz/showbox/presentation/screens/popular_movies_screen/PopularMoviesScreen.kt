package com.treamz.showbox.presentation.screens.popular_movies_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.treamz.showbox.presentation.components.MovieCard
import com.treamz.showbox.presentation.screens.home_screen.components.popular_movies_row.PopularMovieRowViewModel


@Composable
fun PopularMoviesScreen(
    popularMovieRowViewModel: PopularMovieRowViewModel = hiltViewModel(),
    onClick: (movieId: Int) -> Unit
) {
    val popularMovie = popularMovieRowViewModel.state.value.popularMovies.collectAsLazyPagingItems()
    Column() {

        LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp)) {
            items(popularMovie.itemCount) {
                MovieCard(popularMovie.get(it), onClick = {
                    onClick(it)
                })
            }
            items(20) {
//                MovieCard(navController = navController)
            }
        }
    }
}