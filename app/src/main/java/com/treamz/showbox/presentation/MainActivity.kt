package com.treamz.showbox.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.treamz.showbox.presentation.screens.auth_screen.AuthViewModel
import com.treamz.showbox.navigation.graphs.Graph
import com.treamz.showbox.navigation.graphs.RootNavigationGraph
import com.treamz.showbox.ui.theme.ShowboxTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    //    private val authViewModel by viewModels<AuthViewModel>()
    private lateinit var navController: NavHostController

    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShowboxTheme(darkTheme = true) {

                navController = rememberNavController()

                // A surface container using the 'background' color from the theme
                RootNavigationGraph(navController = navController)
                checkAuthState()

            }
        }
    }

    private fun checkAuthState() {
//        if (viewModel.isUserAuthenticated) {
//            navController.navigate(Graph.ROOT)
//        } else {
//            navController.navigate(Graph.AUTH)
//        }
    }

}
