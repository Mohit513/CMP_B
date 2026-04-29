package com.example.cmp_b

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.cmp_b.ui.PostListScreen
import com.example.cmp_b.ui.PostViewModel
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    KoinContext {
        val viewModel: PostViewModel = koinViewModel()
        MaterialTheme {
            PostListScreen(viewModel)
        }
    }
}
