package com.treamz.showbox.presentation.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.treamz.showbox.presentation.home_screen.HomeScreen
import com.treamz.showbox.presentation.movie_discover.MovieDiscoverScreen
import com.treamz.showbox.presentation.popular_movies_screen.PopularMoviesScreen

@Composable
fun RootNavigationGraph(navController: NavHostController){
    NavHost(navController = navController, route = Graph.ROOT, startDestination = Graph.HOME) {
        composable(route = Graph.HOME) {
            HomeScreen()
        }


    }
}

object Graph {
    const val ROOT = "root_graph"
    const val HOME = "home_graph"
}