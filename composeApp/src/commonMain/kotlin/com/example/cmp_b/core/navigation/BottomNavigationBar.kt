package com.example.cmp_b.core.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import org.koin.compose.koinInject

/**
 * Bottom navigation bar component
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(
    navigationController: NavController = koinInject(),
    modifier: Modifier = Modifier
) {
    val bottomNavDestinations = navigationController.getBottomNavDestinations()
    val currentRoute = navigationController.getCurrentRoute()
    
    NavigationBar(
        modifier = modifier
    ) {
        bottomNavDestinations.forEach { destination ->
            NavigationBarItem(
                icon = {
                    Text(
                        text = getIconForRoute(destination.route),
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                label = { Text(destination.title) },
                selected = currentRoute == destination.route,
                onClick = {
                    navigationController.navigate(destination.route)
                }
            )
        }
    }
}

/**
 * Get icon for a given route
 */
private fun getIconForRoute(route: String): String {
    return when (route) {
        "home" -> "🏠"
        "profile" -> "👤"
        "settings" -> "⚙️"
        else -> "🏠"
    }
}
