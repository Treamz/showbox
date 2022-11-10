package com.treamz.showbox.presentation.screens.auth_screen.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.treamz.showbox.domain.models.Response
import com.treamz.showbox.presentation.screens.auth_screen.AuthViewModel
import com.treamz.showbox.presentation.components.ProgressBar

@Composable
fun OneTapSignIn(
    viewModel: AuthViewModel = hiltViewModel(),
    launch: (result: BeginSignInResult) -> Unit
) {
    val context = LocalContext.current

    when(val oneTapSignInResponse = viewModel.oneTapSignInResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> oneTapSignInResponse.data?.let {
            LaunchedEffect(it) {
                launch(it)
            }
        }
        is Response.Failure -> LaunchedEffect(Unit) {
            print(oneTapSignInResponse.e)
            Toast.makeText(context,oneTapSignInResponse.e.localizedMessage,Toast.LENGTH_LONG).show()
        }
    }
}