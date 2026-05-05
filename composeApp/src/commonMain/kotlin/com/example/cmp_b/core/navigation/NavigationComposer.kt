package com.example.cmp_b.core.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.cmp_b.core.navigation.destinations.HomeDestination
import com.example.cmp_b.core.navigation.destinations.PostDetailDestination
import com.example.cmp_b.core.navigation.destinations.ProfileDestination
import com.example.cmp_b.core.navigation.destinations.SettingsDestination
import org.koin.compose.koinInject

/**
 * Main navigation composer that combines navigation host with bottom navigation
 */
@Composable
fun NavigationComposer(
    navigationManager: NavigationManager = koinInject(),
    navigationController: NavController = koinInject(),
    destinations: List<NavigationDestination> = koinInject(),
    modifier: Modifier = Modifier
) {
    // Initialize with home destination if no current destination
    LaunchedEffect(destinations) {
        if (navigationManager.currentDestination.value == null) {
            val homeDestination = destinations.find { it.route == "home" }
            homeDestination?.let {
                navigationManager.navigate(it)
            }
        }
    }
    
    val currentDestination by navigationManager.currentDestination
    val showBottomNav = currentDestination?.showInBottomNav == true
    
    Scaffold(
        bottomBar = {
            if (showBottomNav) {
                BottomNavigationBar(
                    navigationController = navigationController,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    ) { padding ->
        NavigationHost(
            navigationManager = navigationManager,
            destinations = destinations,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .then(modifier)
        )
    }
}

/**
 * Navigation graph composer for setting up all destinations
 */
@Composable
fun rememberNavigationGraph(): List<NavigationDestination> {
    return remember {
        listOf(
            HomeDestination,
            ProfileDestination,
            SettingsDestination,
            PostDetailDestination
        )
    }
}
