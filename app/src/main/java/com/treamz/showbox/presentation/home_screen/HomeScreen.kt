package com.treamz.showbox.presentation.home_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.treamz.showbox.presentation.BottomBarScreen
import com.treamz.showbox.presentation.auth_screen.AuthViewModel
import com.treamz.showbox.presentation.navigation.graphs.HomeNavGraph


@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
//        TopAppBar(backgroundColor = Color.Black) {
//            Text(text = "SHOWBOX", modifier = Modifier.padding(start = 5.dp), style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 23.sp))
//        }
        Box(modifier = Modifier.padding(it)) {
            HomeNavGraph(navController = navController)
        }

    }
}


@Composable
fun BottomBar(navController: NavHostController, authViewModel: AuthViewModel = hiltViewModel()) {

    val screens = listOf(
        BottomBarScreen.Home,
        if (authViewModel.isUserAuthenticated) BottomBarScreen.Profile else BottomBarScreen.Auth,
        BottomBarScreen.Settings,
        BottomBarScreen.Search,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        BottomNavigation {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}