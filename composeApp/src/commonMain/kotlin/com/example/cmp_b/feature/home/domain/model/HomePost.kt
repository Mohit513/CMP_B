package com.example.cmp_b.feature.home.domain.model

data class HomePost(
    val id: Int,
    val title: String,
    val body: String,
    val userId: Int,
    val isFavorite: Boolean = false
)
