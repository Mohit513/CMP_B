package com.example.cmp_b.feature.home.presentation.intent

sealed interface HomeScreenIntent {
    object LoadPosts : HomeScreenIntent
    object RefreshPosts : HomeScreenIntent
    data class ToggleFavorite(val postId: Int) : HomeScreenIntent
}
