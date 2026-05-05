package com.example.cmp_b.shared.presentation.state

import com.example.cmp_b.shared.domain.model.Post

data class PostListState(
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
