package com.example.cmp_b.core.navigation.destinations

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cmp_b.core.navigation.BaseNavigationDestination
import com.example.cmp_b.core.navigation.NamedNavArgument
import com.example.cmp_b.core.navigation.NavType
import org.koin.compose.koinInject

object ProfileDestination : BaseNavigationDestination() {
    override val route: String = "profile"
    override val routeWithArgs: String = "profile/{userId}"
    override val title: String = "Profile"
    override val iconRes: String = "person"
    override val showInBottomNav: Boolean = true
    
    override val arguments: List<NamedNavArgument> = listOf(
        NamedNavArgument("userId", NavType.STRING, nullable = true, defaultValue = "current_user")
    )
    
    @Composable
    override fun Content(modifier: Modifier) {
        ProfileScreen(modifier = modifier)
    }
    
    fun createRoute(userId: String = "current_user"): String {
        return createRoute("userId" to userId)
    }
}

@Composable
private fun ProfileScreen(
    modifier: Modifier = Modifier,
    navigationController: com.example.cmp_b.core.navigation.NavController = koinInject()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Profile Screen",
            style = MaterialTheme.typography.headlineMedium
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "User profile and settings",
            style = MaterialTheme.typography.bodyLarge
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Button(
            onClick = { navigationController.navigateBack() }
        ) {
            Text("Go Back")
        }
    }
}
