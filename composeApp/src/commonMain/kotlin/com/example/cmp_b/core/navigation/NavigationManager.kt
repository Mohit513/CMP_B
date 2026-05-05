package com.example.cmp_b.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable

/**
 * Navigation state manager
 */
class NavigationManager {
    
    private val _currentDestination = mutableStateOf<NavigationDestination?>(null)
    val currentDestination: MutableState<NavigationDestination?> = _currentDestination
    
    private val _navigationStack = mutableStateOf<List<NavigationDestination>>(emptyList())
    val navigationStack: MutableState<List<NavigationDestination>> = _navigationStack
    
    /**
     * Navigate to a destination
     */
    fun navigate(destination: NavigationDestination) {
        val currentStack = _navigationStack.value.toMutableList()
        
        // Remove current destination if it exists in stack (to avoid duplicates)
        if (_currentDestination.value != null) {
            currentStack.remove(_currentDestination.value)
        }
        
        // Add current destination to stack if it's not the same as the new one
        if (_currentDestination.value != destination) {
            _currentDestination.value?.let { currentStack.add(it) }
        }
        
        _currentDestination.value = destination
        _navigationStack.value = currentStack
    }
    
    /**
     * Navigate back
     */
    fun navigateBack(): Boolean {
        val currentStack = _navigationStack.value.toMutableList()
        
        return if (currentStack.isNotEmpty()) {
            val previousDestination = currentStack.removeLast()
            _currentDestination.value = previousDestination
            _navigationStack.value = currentStack
            true
        } else {
            false
        }
    }
    
    /**
     * Navigate to a specific destination by route
     */
    fun navigateToRoute(route: String, destinations: List<NavigationDestination>) {
        val destination = destinations.find { it.route == route }
        if (destination != null) {
            navigate(destination)
        }
    }
    
    /**
     * Clear navigation stack and navigate to destination
     */
    fun navigateAndClearStack(destination: NavigationDestination) {
        _currentDestination.value = destination
        _navigationStack.value = emptyList()
    }
    
    /**
     * Get current route
     */
    fun getCurrentRoute(): String? = _currentDestination.value?.route
    
    /**
     * Check if can navigate back
     */
    fun canNavigateBack(): Boolean = _navigationStack.value.isNotEmpty()
}

/**
 * Remember navigation manager
 */
@Composable
fun rememberNavigationManager(): NavigationManager {
    return rememberSaveable(
        saver = Saver(
            save = { it.getCurrentRoute() },
            restore = { NavigationManager() }
        )
    ) {
        NavigationManager()
    }
}
