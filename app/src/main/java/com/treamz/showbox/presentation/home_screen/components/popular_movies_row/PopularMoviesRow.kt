package com.treamz.showbox.presentation.home_screen.components.popular_movies_row

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.treamz.showbox.presentation.components.MovieCard


@Composable
fun PopularMoviesRow(
    onClick: (movieId: Int) -> Unit,
    onClickPopularMovies: () -> Unit,
    popularMovieRowViewModel: PopularMovieRowViewModel
) {
    val popularMovie = popularMovieRowViewModel.state.value.popularMovies.collectAsLazyPagingItems()
    Column() {
        Row(verticalAlignment = Alignment.CenterVertically) {
//            Text(text = "Popular Movies", color = Color.White, modifier = Modifier.padding(start = 10.dp))
            TextButton(onClick = { onClickPopularMovies() }, modifier = Modifier.padding(start = 10.dp)) {
                Text(text = "Popular Movies")
            }

        }
        LazyRow(userScrollEnabled = true) {
            items(popularMovie) {
                MovieCard(it, onClick = {
                    onClick(it)
                })
            }
            items(20) {
//                MovieCard(navController = navController)
            }
        }
    }
}