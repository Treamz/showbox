package com.treamz.showbox.navigation.graphs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.treamz.showbox.presentation.BottomBarScreen
import com.treamz.showbox.presentation.screens.auth_screen.AuthScreen
import com.treamz.showbox.presentation.screens.auth_screen.AuthViewModel
import com.treamz.showbox.presentation.screens.home_screen.HomeScreen
import com.treamz.showbox.presentation.screens.movie_details.MovieDetailsScreen
import com.treamz.showbox.presentation.screens.movie_discover.MovieDiscoverScreen
import com.treamz.showbox.presentation.screens.popular_movies_screen.PopularMoviesScreen
import com.treamz.showbox.presentation.screens.profile_screen.ProfileScreen
import com.treamz.showbox.presentation.screens.profile_screen.ProfileViewModel
import com.treamz.showbox.presentation.screens.show_details.ShowDetailsScreen
import com.treamz.showbox.presentation.screens.splash_screen.SplashScreen

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, route = Graph.ROOT, startDestination = Graph.HOME) {
        composable(route = Graph.SPLASH) {
            SplashScreen(setNewDestination = {}, navHostController = navController)
        }

        composable(route = Graph.AUTH) {
            val authViewModel = hiltViewModel<AuthViewModel>()

            AuthScreen(viewModel = authViewModel,
                navigateToProfileScreen = {
                    navController.navigate(Graph.HOME)
                }
            )
        }
        composable(route = Graph.HOME) {
            HomeScreen(navController)
        }

    }
}


object Graph {
    const val ROOT = "root_graph"
    const val HOME = "home_graph"
    const val HOME2 = "home2_graph"
    const val SPLASH = "splash_graph"
    const val AUTH = "auth_graph"
}