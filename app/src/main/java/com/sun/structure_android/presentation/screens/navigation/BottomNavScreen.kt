package com.sun.structure_android.presentation.screens.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sun.structure_android.R

abstract class AppScreen(val route: String) {
}

sealed class BottomNavScreen(
    route: String, @DrawableRes val unselectedIcon: Int,
    @DrawableRes val selectedIcon: Int,
) : AppScreen(route) {

    data object Home : BottomNavScreen(
        route = "home",
        unselectedIcon = R.drawable.ic_film_unselected,
        selectedIcon = R.drawable.ic_film_selected,
    )

    data object Bookmark : BottomNavScreen(
        route = "Bookmark",
        unselectedIcon = R.drawable.ic_bookmark_unselected,
        selectedIcon = R.drawable.ic_bookmark_selected,
    )

    data object Profile : BottomNavScreen(
        route = "profile",
        unselectedIcon = R.drawable.ic_ticket_unselected,
        selectedIcon = R.drawable.ic_ticket_selected,
    )
}
