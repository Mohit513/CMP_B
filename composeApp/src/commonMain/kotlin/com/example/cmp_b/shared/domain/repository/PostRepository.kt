package com.example.cmp_b.shared.domain.repository

import com.example.cmp_b.core.data.network.api.common.NetworkResult
import com.example.cmp_b.shared.domain.model.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getPosts(): Flow<NetworkResult<List<Post>>>
    fun getPostById(id: Int): Flow<NetworkResult<Post>>
    fun createPost(post: Post): Flow<NetworkResult<Post>>
    fun updatePost(id: Int, post: Post): Flow<NetworkResult<Post>>
    fun deletePost(id: Int): Flow<NetworkResult<Unit>>
}
