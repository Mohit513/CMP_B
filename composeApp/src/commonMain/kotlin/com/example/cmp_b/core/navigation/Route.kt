package com.example.cmp_b.core.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object PostList : Route

    @Serializable
    data class PostDetail(val id: Int) : Route
}
