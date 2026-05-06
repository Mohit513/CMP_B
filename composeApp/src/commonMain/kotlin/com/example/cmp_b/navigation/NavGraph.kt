package com.example.cmp_b.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cmp_b.ui.PostListScreen
import com.example.cmp_b.ui.PostViewModel
import com.example.cmp_b.ui.auth.LoginScreen
import com.example.cmp_b.ui.auth.LoginViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NavGraph(appState: AppState) {
    Scaffold(
        snackbarHost = { SnackbarHost(appState.snackbarHostState) }
    ) { innerPadding ->
        NavHost(
            navController = appState.navigator.navController,
            startDestination = NavRoutes.Login.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavRoutes.Login.route) {
                val viewModel: LoginViewModel = koinViewModel()
                LoginScreen(appState, viewModel)
            }
            composable(NavRoutes.PostList.route) {
                val viewModel: PostViewModel = koinViewModel()
                PostListScreen(viewModel)
            }
        }
    }
}
