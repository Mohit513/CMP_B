package com.example.cmp_b.core.navigation.destinations

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cmp_b.core.navigation.BaseNavigationDestination
import org.koin.compose.koinInject

object SettingsDestination : BaseNavigationDestination() {
    override val route: String = "settings"
    override val title: String = "Settings"
    override val iconRes: String = "settings"
    override val showInBottomNav: Boolean = true
    
    @Composable
    override fun Content(modifier: Modifier) {
        SettingsScreen(modifier = modifier)
    }
}

@Composable
private fun SettingsScreen(
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
            text = "Settings Screen",
            style = MaterialTheme.typography.headlineMedium
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "App settings and preferences",
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
