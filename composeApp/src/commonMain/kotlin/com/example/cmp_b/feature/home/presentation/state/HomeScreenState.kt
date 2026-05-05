package com.example.cmp_b.feature.home.presentation.state

import com.example.cmp_b.feature.home.domain.model.HomePost

data class HomeScreenState(
    val posts: List<HomePost> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isRefreshing: Boolean = false
)
