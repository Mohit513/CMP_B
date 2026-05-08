package com.example.cmp_b.core.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class PostDto(
    val id: Int,
    val title: String,
    val body: String,
    val userId: Int
)
