package com.example.cmp_b.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CommentDto(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)
