package com.example.cmp_b.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cmp_b.core.session.SessionManager
import com.example.cmp_b.ui.auth.LoginScreen
import com.example.cmp_b.ui.auth.LoginViewModel
import com.example.cmp_b.ui.dashboard.DashboardViewModel
import com.example.cmp_b.ui.dashboard.DigiDashboardScreen
import com.example.cmp_b.ui.post_list.PostListScreen
import com.example.cmp_b.ui.post_list.PostViewModel
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NavGraph(appState: AppState) {
    val sessionManager: SessionManager = koinInject()
    val startDestination = if (sessionManager.isLoggedIn) {
        NavRoutes.DigiDashboard.route
    } else {
        NavRoutes.Login.route
    }

    Scaffold(
        snackbarHost = { SnackbarHost(appState.snackbarHostState) }
    ) { innerPadding ->
        NavHost(
            navController = appState.navigator.navController,
            startDestination = startDestination,
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
            composable(NavRoutes.DigiDashboard.route) {
                val viewModel: DashboardViewModel = koinViewModel()
                DigiDashboardScreen(appState, viewModel)
            }
        }
    }
}
