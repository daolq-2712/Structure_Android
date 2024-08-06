package com.sun.structure_android.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sun.structure_android.data.repository.MovieRepositoryImpl
import com.sun.structure_android.navigation.movie.MovieDestination
import com.sun.structure_android.presentation.screens.navigation.BottomNavScreen
import com.sun.structure_android.presentation.screens.bookmark.BookmarkScreen
import com.sun.structure_android.presentation.screens.home.HomeScreen
import com.sun.structure_android.presentation.screens.home.HomeViewModel
import com.sun.structure_android.presentation.screens.moviedetail.MovieDetailScreen
import com.sun.structure_android.presentation.screens.moviedetail.MovieDetailViewModel
import com.sun.structure_android.presentation.screens.profile.ProfileScreen
import com.sun.structure_android.shared.KEY_MOVIE_ID
import com.sun.structure_android.shared.extension.navigate

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(bottomBar = {
        BottomNavigation {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            val bottomNavScreens = listOf(
                BottomNavScreen.Home,
                BottomNavScreen.Bookmark,
                BottomNavScreen.Profile
            )
            bottomNavScreens.forEach { screen ->
                val selected =
                    currentDestination?.hierarchy?.any { it.route == screen.route } == true
                BottomNavigationItem(
                    selected = selected,
                    icon = {
                        Icon(
                            painter = painterResource(id = if (selected) screen.selectedIcon else screen.unselectedIcon),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    },
                    onClick = {
                        navController.navigate(screen.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    },
                )
            }
        }
    }) { innerPadding ->
        val homeViewModel = HomeViewModel(MovieRepositoryImpl())
        NavHost(
            navController = navController,
            startDestination = BottomNavScreen.Home.route,
            Modifier.padding(innerPadding)
        ) {
            composable(route = BottomNavScreen.Home.route) {
                HomeScreen(
                    viewModel = homeViewModel,
                    navigator = { destination ->
                        navController.navigate(destination)
                    },
                )
            }
            composable(route = BottomNavScreen.Bookmark.route) {
                BookmarkScreen("Bookmark Screen")
            }
            composable(route = BottomNavScreen.Profile.route) {
                ProfileScreen("Profile Screen")
            }
            composable(
                route = MovieDestination.MovieDetail.route,
                arguments = MovieDestination.MovieDetail.arguments
            ) {
                navBackStackEntry ->
                val movieId = navBackStackEntry.arguments?.getString(KEY_MOVIE_ID)
                val savedStateHandle = SavedStateHandle()
                savedStateHandle[KEY_MOVIE_ID] = movieId
                MovieDetailScreen(
                    viewModel = MovieDetailViewModel(savedStateHandle, MovieRepositoryImpl()),
                    navigator = { destination ->
                        navController.navigate(destination)
                    },
                )
            }
        }
    }
}
