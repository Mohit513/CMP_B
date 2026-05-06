package com.example.cmp_b

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.cmp_b.core.navigation.SetupNavGraph
import org.koin.compose.KoinContext

@Composable
fun App() {
    KoinContext {
        MaterialTheme {
            val navController = rememberNavController()
            SetupNavGraph(navController = navController)
        }
    }
}
