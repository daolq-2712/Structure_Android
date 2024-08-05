package com.sun.structure_android.navigation.movie

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.sun.structure_android.navigation.BaseDestination
import com.sun.structure_android.shared.KEY_MOVIE_ID

sealed class MovieDestination {
    object MovieDetail : BaseDestination("movie/{$KEY_MOVIE_ID}") {
        override val arguments = listOf(
            navArgument(KEY_MOVIE_ID) { type = NavType.StringType }
        )

        fun createRoute(id: String) = apply {
            destination = "movie/$id"
        }
    }
}
