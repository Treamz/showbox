package com.treamz.showbox.presentation.screens.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.treamz.showbox.R
import com.treamz.showbox.presentation.screens.auth_screen.AuthViewModel
import com.treamz.showbox.ui.theme.ShowboxTheme

sealed class DrawerScreens(val title: String) {
    object Home : DrawerScreens("Home")
    object Account : DrawerScreens("Account")
    object Help : DrawerScreens( "Help")
}

private val screens = listOf(
    DrawerScreens.Home,
    DrawerScreens.Account,
    DrawerScreens.Help
)

@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    onDestinationClicked: (route: String) -> Unit,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(start = 24.dp, top = 48.dp)
    ) {
//        authViewModel.currentUser.
        Text(text = "${authViewModel.currentUser.toString()}")

        Image(
            painter = painterResource(coil.base.R.drawable.ic_100tb),
            contentDescription = "App icon"
        )
        screens.forEach { screen ->
            Spacer(Modifier.height(24.dp))
            Text(
                text = screen.title,
                style = MaterialTheme.typography.h4,
                modifier = Modifier.clickable {
                    onDestinationClicked(screen.title)
                }
            )
        }
    }
}
