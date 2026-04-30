package com.example.cmp_b.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class AppState(
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
    navigator: AppNavigator = rememberAppNavigator(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): AppState {
    return remember(navigator, snackbarHostState, coroutineScope) {
        AppState(navigator, snackbarHostState, coroutineScope)
    }
}
