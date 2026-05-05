package com.example.cmp_b.core.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.compose.koinInject

/**
 * Navigation host that manages screen switching
 */
@Composable
fun NavigationHost(
    navigationManager: NavigationManager = koinInject(),
    destinations: List<NavigationDestination> = koinInject(),
    modifier: Modifier = Modifier
) {
    val currentDestination by navigationManager.currentDestination
    
    // Find the current destination and render its content
    currentDestination?.let { destination ->
        destination.Content(modifier = modifier.fillMaxSize())
    }
}

/**
 * Navigation controller for handling navigation actions
 */
@Composable
fun rememberNavigationController(
    navigationManager: NavigationManager = koinInject(),
    destinations: List<NavigationDestination> = koinInject()
): NavController {
    return remember(navigationManager, destinations) {
        NavController(navigationManager, destinations)
    }
}

/**
 * Navigation controller class
 */
class NavController(
    private val navigationManager: NavigationManager,
    private val destinations: List<NavigationDestination>
) {
    
    /**
     * Navigate to a destination
     */
    fun navigate(destination: NavigationDestination) {
        navigationManager.navigate(destination)
    }
    
    /**
     * Navigate by route
     */
    fun navigate(route: String) {
        navigationManager.navigateToRoute(route, destinations)
    }
    
    /**
     * Navigate back
     */
    fun navigateBack(): Boolean {
        return navigationManager.navigateBack()
    }
    
    /**
     * Navigate and clear stack
     */
    fun navigateAndClearStack(destination: NavigationDestination) {
        navigationManager.navigateAndClearStack(destination)
    }
    
    /**
     * Get current route
     */
    fun getCurrentRoute(): String? = navigationManager.getCurrentRoute()
    
    /**
     * Check if can navigate back
     */
    fun canNavigateBack(): Boolean = navigationManager.canNavigateBack()
    
    /**
     * Get all destinations
     */
    fun getDestinations(): List<NavigationDestination> = destinations
    
    /**
     * Get bottom navigation destinations
     */
    fun getBottomNavDestinations(): List<NavigationDestination> {
        return destinations.filter { it.showInBottomNav }
    }
}
