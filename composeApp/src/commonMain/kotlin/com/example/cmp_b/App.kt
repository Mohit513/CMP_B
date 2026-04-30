package com.example.cmp_b

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cmp_b.ui.PostListScreen
import com.example.cmp_b.ui.PostViewModel
import org.koin.compose.KoinContext
import org.koin.compose.currentKoinScope

@Composable
fun App() {
    KoinContext {
        val koinScope = currentKoinScope()
        val viewModel: PostViewModel = viewModel { koinScope.get<PostViewModel>() }
        MaterialTheme {
            PostListScreen(viewModel)
        }
    }
}
