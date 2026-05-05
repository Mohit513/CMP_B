package com.example.cmp_b.shared.presentation.intent

sealed interface PostListIntent {
    object LoadPosts : PostListIntent
    object RefreshPosts : PostListIntent
}
