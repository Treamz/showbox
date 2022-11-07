package com.treamz.showbox.presentation.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.treamz.showbox.presentation.components.MovieCard
import com.treamz.showbox.presentation.home_screen.components.popular_movies_row.PopularMovieRowViewModel
import com.treamz.showbox.presentation.home_screen.components.popular_movies_row.PopularMoviesRow
import com.treamz.showbox.presentation.home_screen.components.popular_shows_row.PopularShowsRow
import com.treamz.showbox.presentation.home_screen.components.popular_shows_row.PopularShowsRowViewModel
import com.treamz.showbox.presentation.home_screen.components.top_rated_movie_row.TopRatedMovieRowViewModel
import com.treamz.showbox.presentation.home_screen.components.top_rated_movie_row.TopRatedMoviesRow
import com.treamz.showbox.presentation.home_screen.components.top_rated_shows_row.TopRatedShowsRow
import com.treamz.showbox.presentation.home_screen.components.top_rated_shows_row.TopRatedShowsRowViewModel
import com.treamz.showbox.presentation.navigation.graphs.DetailsScreen
import com.treamz.showbox.presentation.navigation.graphs.Graph

@Composable
fun HomeTab(
    navController: NavController,
    popularMovieRowViewModel: PopularMovieRowViewModel,
    topRatedMovieRowViewModel: TopRatedMovieRowViewModel,
    popularShowsRowViewModel: PopularShowsRowViewModel,
    topRatedShowsRowViewModel: TopRatedShowsRowViewModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .wrapContentSize(Alignment.Center)
    ) {
        item {
            PopularMoviesRow(
                popularMovieRowViewModel = popularMovieRowViewModel,
                onClick = {
                    navController.navigate("movieDetails/$it")
                }, onClickPopularMovies = {
                    navController.navigate(DetailsScreen.PopularMovies.route)
                })


            TopRatedMoviesRow(topRatedMovieRowViewModel = topRatedMovieRowViewModel, onClick = {
                navController.navigate("movieDetails/$it")
            })
            PopularShowsRow(
                popularShowsRowViewModel = popularShowsRowViewModel, onClick = {
                    navController.navigate("showDetails/$it")
                }, onClickPopularMovies = {
                    navController.navigate(DetailsScreen.PopularMovies.route)
                })

            TopRatedShowsRow(topRatedShowsRowViewModel = topRatedShowsRowViewModel, onClick = {
                navController.navigate("showDetails/$it")
            })


        }
    }
}


@Composable
fun topRatedMoviesRow(navController: NavController) {

    Column() {
        Text(
            text = "Top Rated Movies",
            color = Color.White,
            modifier = Modifier.padding(start = 10.dp)
        )
        LazyRow(userScrollEnabled = true) {

            items(20) {
//                MovieCard(navController = navController)
            }
        }
    }
}


@Composable
fun popularShowsRow(navController: NavController) {

    Column() {
        Text(
            text = "Popular Movies",
            color = Color.White,
            modifier = Modifier.padding(start = 10.dp)
        )
        LazyRow(userScrollEnabled = true) {

            items(20) {
//                MovieCard(navController = navController)
            }
        }
    }
}