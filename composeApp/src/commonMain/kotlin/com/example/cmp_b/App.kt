package com.example.cmp_b

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cmp_b.core.navigation.NavigationComposer

@Composable
fun App() {
    MaterialTheme {
        NavigationComposer(
            modifier = Modifier
        )
    }
}
