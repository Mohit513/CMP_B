package com.example.cmp_b.core.navigation.destinations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cmp_b.core.navigation.BaseNavigationDestination
import com.example.cmp_b.feature.home.presentation.screen.HomeScreen

object HomeDestination : BaseNavigationDestination() {
    override val route: String = "home"
    override val title: String = "Home"
    override val iconRes: String = "home"
    override val showInBottomNav: Boolean = true
    
    @Composable
    override fun Content(modifier: Modifier) {
        HomeScreen(modifier = modifier)
    }
}
