package com.example.cmp_b.core.navigation.destinations

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cmp_b.core.navigation.BaseNavigationDestination
import com.example.cmp_b.core.navigation.NamedNavArgument
import com.example.cmp_b.core.navigation.NavType
import org.koin.compose.koinInject

object PostDetailDestination : BaseNavigationDestination() {
    override val route: String = "post_detail"
    override val routeWithArgs: String = "post_detail/{postId}"
    override val title: String = "Post Detail"
    override val showInBottomNav: Boolean = false
    
    override val arguments: List<NamedNavArgument> = listOf(
        NamedNavArgument("postId", NavType.INT, nullable = false)
    )
    
    @Composable
    override fun Content(modifier: Modifier) {
        PostDetailScreen(modifier = modifier)
    }
    
    fun createRoute(postId: Int): String {
        return createRoute("postId" to postId)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PostDetailScreen(
    modifier: Modifier = Modifier,
    navigationController: com.example.cmp_b.core.navigation.NavController = koinInject()
) {
    // In a real app, you would get the postId from navigation arguments
    // For now, we'll just show a sample detail screen
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Post Detail") },
                navigationIcon = {
                    IconButton(onClick = { navigationController.navigateBack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Post Detail Screen",
                style = MaterialTheme.typography.headlineMedium
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "Detailed view of a specific post",
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
}
