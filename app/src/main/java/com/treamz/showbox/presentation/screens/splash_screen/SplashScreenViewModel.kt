package com.treamz.showbox.presentation.screens.splash_screen

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.treamz.showbox.domain.repository.AuthRepository
import com.treamz.showbox.navigation.graphs.Graph
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val repository: AuthRepository,

    ) : ViewModel() {

    init {

    }
    val isUserAuthenticated get() = repository.isUserAuthenticatedInFirebase

    fun navigate(navHostController: NavHostController) {
        if (isUserAuthenticated) {
            navHostController.navigate(Graph.HOME)
        } else {
            navHostController.navigate(Graph.AUTH)
        }
    }

}