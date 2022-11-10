package com.treamz.showbox.presentation.screens.home_screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.treamz.showbox.presentation.BottomBarScreen
import com.treamz.showbox.presentation.screens.auth_screen.AuthViewModel
import com.treamz.showbox.navigation.graphs.HomeNavGraph
import com.treamz.showbox.presentation.screens.drawer.Drawer
import com.treamz.showbox.presentation.screens.profile_screen.ProfileViewModel
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(
    parentNavController: NavHostController,
    navController: NavHostController = rememberNavController(),
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val openDrawer = {
        println("DRAWER")
        scope.launch {
            scaffoldState.drawerState.open()
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            Drawer(

                onDestinationClicked = { route ->
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                    navController.navigate(route) {
//                        popUpTo = navController.graph.route
                        launchSingleTop = true
                    }
                }
            )
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "HORIZON"
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { openDrawer() } ) {
                        Icon(Icons.Filled.Menu, contentDescription = "")
                    }
                },

                backgroundColor = MaterialTheme.colors.primaryVariant
            )
        },
        bottomBar = {
            BottomBar(navController = navController)
        }
//        bottomBar = { Text(text ="${authViewModel.isAuthorized}" ) }

    ) {
//        TopAppBar(backgroundColor = Color.Black) {
//            Text(text = "SHOWBOX", modifier = Modifier.padding(start = 5.dp), style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 23.sp))
//        }
        Box(modifier = Modifier.padding(it)) {
            HomeNavGraph(navController = navController,parentNavController=parentNavController)
        }

    }
}


@Composable
fun BottomBar(navController: NavHostController) {

    val screens =
        listOf(
            BottomBarScreen.Home,
            BottomBarScreen.Profile,
            BottomBarScreen.Auth,
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
                    navController = navController,
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController,
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