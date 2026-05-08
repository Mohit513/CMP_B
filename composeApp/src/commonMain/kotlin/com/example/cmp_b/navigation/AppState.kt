package com.example.cmp_b.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class AppState(
    val navController: NavHostController,
    val navigator: AppNavigator,
    val snackbarHostState: SnackbarHostState,
    val coroutineScope: CoroutineScope
) {
    fun showSnackBar(message: String) {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(message)
        }
    }
}

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): AppState {
    val navigator = rememberAppNavigator(navController)
    return remember(navController, navigator, snackbarHostState, coroutineScope) {
        AppState(navController, navigator, snackbarHostState, coroutineScope)
    }
}
