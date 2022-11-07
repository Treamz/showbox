package com.treamz.showbox.presentation.navigation.graphs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation


import com.treamz.showbox.presentation.BottomBarScreen
import com.treamz.showbox.presentation.auth_screen.AuthScreen
import com.treamz.showbox.presentation.home_screen.HomeTab
import com.treamz.showbox.presentation.home_screen.components.popular_movies_row.PopularMovieRowViewModel
import com.treamz.showbox.presentation.home_screen.components.popular_shows_row.PopularShowsRowViewModel
import com.treamz.showbox.presentation.home_screen.components.top_rated_movie_row.TopRatedMovieRowViewModel
import com.treamz.showbox.presentation.home_screen.components.top_rated_shows_row.TopRatedShowsRowViewModel
import com.treamz.showbox.presentation.movie_details.MovieDetailsScreen
import com.treamz.showbox.presentation.popular_movies_screen.PopularMoviesScreen
import com.treamz.showbox.presentation.profile_screen.ProfileScreen
import com.treamz.showbox.presentation.screens.settings_screen.SettingsScreen
import com.treamz.showbox.presentation.search_screen.SearchScreen
import com.treamz.showbox.presentation.show_details.ShowDetailsScreen

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            val popularMovieRowViewModel = hiltViewModel<PopularMovieRowViewModel>()
            val topRatedMovieRowViewModel = hiltViewModel<TopRatedMovieRowViewModel>()
            val popularShowsRowViewModel = hiltViewModel<PopularShowsRowViewModel>()
            val topRatedShowsRowViewModel = hiltViewModel<TopRatedShowsRowViewModel>()

            HomeTab(
                navController = navController,
                popularMovieRowViewModel,
                topRatedMovieRowViewModel,
                popularShowsRowViewModel,
                topRatedShowsRowViewModel
            )

        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen() {
                navController.popBackStack()
                navController.navigate(BottomBarScreen.Auth.route)
            }
        }
        composable(route = BottomBarScreen.Auth.route) {
            AuthScreen(
                navigateToProfileScreen = {

//                    navController.navigate(BottomBarScreen.Profile.route)
                }
            )
        }
        composable(route = BottomBarScreen.Settings.route) {
            SettingsScreen()
        }

        composable(route = BottomBarScreen.Search.route) {
            SearchScreen(navController)
        }

        detailsMoviesNavGraph(navController = navController)
        detailsShowsNavGraph(navController = navController)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.detailsMoviesNavGraph(navController: NavHostController) {
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

        composable(
            route = DetailsScreen.InformationShow.route,
            arguments = listOf()
        ) { navBackStackEntry ->
            ShowDetailsScreen(paramId = navBackStackEntry.arguments?.getString("id") ?: "")
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
    object InformationShow : DetailsScreen(route = "INFORMATIONTV")
    object DiscoverMovies : DetailsScreen(route = "DISCOVER_MOVIES")
    object Player : DetailsScreen(route = "PLAYER")
    object PopularMovies : DetailsScreen(route = "POPULAR_MOVIES")
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.detailsShowsNavGraph(navController: NavHostController) {
    navigation(
        route = "showDetails/{movieId}",
        startDestination = DetailsScreen.InformationShow.route,
    ) {
        composable(
            route = DetailsScreen.InformationShow.route,
            arguments = listOf()
        ) { navBackStackEntry ->
            ShowDetailsScreen(paramId = navBackStackEntry.arguments?.getString("id") ?: "")
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