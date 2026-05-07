package com.example.cmp_b

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.cmp_b.navigation.NavGraph
import com.example.cmp_b.navigation.rememberAppState
import org.koin.compose.KoinContext

@Composable
fun App() {
    KoinContext {
        MaterialTheme {
            val appState = rememberAppState()
            NavGraph(appState = appState)
        }
    }
}
