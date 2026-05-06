package com.example.cmp_b.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class AppNavigator(val navController: NavHostController) {
    fun navigate(route: String) {
        navController.navigate(route)
    }

    fun popBack() {
        navController.popBackStack()
    }

    fun popBackWithResult(key: String, value: Any) {
        navController.previousBackStackEntry?.savedStateHandle?.set(key, value)
        navController.popBackStack()
    }

    fun clearAndNavigate(route: String) {
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }
}

@Composable
fun rememberAppNavigator(navController: NavHostController = rememberNavController()): AppNavigator {
    return remember(navController) { AppNavigator(navController) }
}
