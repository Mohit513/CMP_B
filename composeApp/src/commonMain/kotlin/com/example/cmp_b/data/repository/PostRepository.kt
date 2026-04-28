package com.example.cmp_b.data.repository

import com.example.cmp_b.data.model.Post
import com.example.cmp_b.data.remote.ApiService
import com.example.cmp_b.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostRepository(private val apiService: ApiService) {
    fun getPosts(): Flow<NetworkResult<List<Post>>> = flow {
        emit(NetworkResult.Loading)
        try {
            val posts = apiService.getPosts()
            emit(NetworkResult.Success(posts))
        } catch (e: Exception) {
            emit(NetworkResult.Error(e.message ?: "An unknown error occurred"))
        }
    }
}
