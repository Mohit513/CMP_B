package com.example.cmp_b.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Defines a navigation destination in the app
 */
interface NavigationDestination {
    /**
     * Unique route identifier for this destination
     */
    val route: String
    
    /**
     * Title for display purposes (e.g., in toolbar or bottom nav)
     */
    val title: String
    
    /**
     * Icon resource name for bottom navigation (optional)
     */
    val iconRes: String? get() = null
    
    /**
     * Whether this destination should be shown in bottom navigation
     */
    val showInBottomNav: Boolean get() = false
    
    /**
     * Composable function for this destination
     */
    @Composable
    fun Content(modifier: Modifier = Modifier)
}

/**
 * Base class for navigation destinations with optional parameters
 */
abstract class BaseNavigationDestination : NavigationDestination {
    
    /**
     * Full route with parameters (e.g., "profile/{userId}")
     */
    open val routeWithArgs: String = route
    
    /**
     * Arguments for this route
     */
    open val arguments: List<NamedNavArgument> = emptyList()
    
    /**
     * Creates a route with specific arguments
     */
    open fun createRoute(vararg args: Pair<String, Any>): String {
        var route = routeWithArgs
        args.forEach { (key, value) ->
            route = route.replace("{$key}", value.toString())
        }
        return route
    }
}

/**
 * Named navigation argument
 */
data class NamedNavArgument(
    val name: String,
    val type: NavType,
    val nullable: Boolean = false,
    val defaultValue: Any? = null
)

/**
 * Navigation argument types
 */
enum class NavType {
    STRING,
    INT,
    LONG,
    FLOAT,
    DOUBLE,
    BOOLEAN
}
