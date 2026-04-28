package com.example.cmp_b

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cmp_b.data.remote.ApiService
import com.example.cmp_b.data.repository.PostRepository
import com.example.cmp_b.ui.PostListScreen
import com.example.cmp_b.ui.PostViewModel

@Composable
fun App() {
    val apiService = remember { ApiService.create() }
    val repository = remember { PostRepository(apiService) }
    val viewModel: PostViewModel = viewModel { PostViewModel(repository) }

    MaterialTheme {
        PostListScreen(viewModel)
    }
}
