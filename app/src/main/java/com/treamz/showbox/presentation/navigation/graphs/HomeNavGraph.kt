package com.treamz.showbox.presentation.navigation.graphs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation


import com.treamz.showbox.presentation.BottomBarScreen
import com.treamz.showbox.presentation.home_screen.HomeTab
import com.treamz.showbox.presentation.movie_details.MovieDetailsScreen
import com.treamz.showbox.presentation.popular_movies_screen.PopularMoviesScreen

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeTab(navController = navController)

        }
        composable(route = BottomBarScreen.Profile.route) {
//            ScreenContent(
//                "",
//                name = BottomBarScreen.Profile.route,
//                onClick = { }
//            )
        }
        composable(route = BottomBarScreen.Settings.route) {
//            ScreenContent(
//                "",
//                name = BottomBarScreen.Settings.route,
//                onClick = { }
//            )
        }
        detailsNavGraph(navController = navController)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = "movieDetails/{movieId}",
        startDestination = DetailsScreen.Information.route,
    ) {
        composable(
            route = DetailsScreen.Information.route,
            arguments = listOf()
        ) { navBackStackEntry ->
//            ScreenContent(
//                name = DetailsScreen.Information.route,
//                paramId = navBackStackEntry.arguments?.getString("id") ?: ""
//            ) {
//                navController.navigate(DetailsScreen.Overview.route)
//            }
            MovieDetailsScreen(paramId = navBackStackEntry.arguments?.getString("id") ?: "")
        }
        composable(route = DetailsScreen.Player.route) {
//            SourceChooser()
//            ScreenContent(
//                "",
//                name = DetailsScreen.Player.route
//            ) {
//                navController.popBackStack(
//                    route = DetailsScreen.Information.route,
//                    inclusive = false
//                )
//            }
        }
        composable(route = DetailsScreen.DiscoverMovies.route) {
//            MovieDiscover(navController = navController)
        }
        composable(route = DetailsScreen.PopularMovies.route) {
            PopularMoviesScreen(onClick = {
                navController.navigate("movieDetails/$it")
            })
        }
    }
}

sealed class DetailsScreen(val route: String) {
    object Information : DetailsScreen(route = "INFORMATION")
    object DiscoverMovies : DetailsScreen(route = "DISCOVER_MOVIES")
    object Player : DetailsScreen(route = "PLAYER")
    object PopularMovies : DetailsScreen(route = "POPULAR_MOVIES")
}