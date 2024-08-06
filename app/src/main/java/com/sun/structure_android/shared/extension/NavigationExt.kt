package com.sun.structure_android.shared.extension

import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.navOptions
import com.sun.structure_android.navigation.BaseDestination

/**
 * Navigate to provided [BaseDestination] with a Pair of key value String and Data [parcel]
 * Caution to use this method. This method use savedStateHandle to store the Parcelable data.
 * When previousBackstackEntry is popped out from navigation stack, savedStateHandle will return null and cannot retrieve data.
 * eg.Login -> Home, the Login screen will be popped from the back-stack on logging in successfully.
 */
fun NavHostController.navigate(
    destination: BaseDestination,
    parcel: Pair<String, Any?>? = null,
    builder: (NavOptionsBuilder.() -> Unit)? = null,
) {
    when (destination) {
        is BaseDestination.Up -> {
            destination.results.forEach { (key, value) ->
                previousBackStackEntry?.savedStateHandle?.set(key, value)
            }
            navigateUp()
        }

        is BaseDestination.PopBackStack -> {
            destination.results.forEach { (key, value) ->
                getBackStackEntry(destination.targetDestination.route).savedStateHandle[key] = value
            }
            popBackStack(destination.targetDestination.route, inclusive = destination.inclusive)
        }

        else -> {
            parcel?.let { (key, value) ->
                currentBackStackEntry?.savedStateHandle?.set(key, value)
            }
            if (builder != null) {
                navigate(destination.destination, navOptions(builder))
            } else {
                navigate(destination.destination)
            }
        }
    }
}
