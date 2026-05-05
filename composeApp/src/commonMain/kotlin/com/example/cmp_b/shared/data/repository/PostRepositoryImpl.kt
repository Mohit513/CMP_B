package com.example.cmp_b.shared.data.repository

import com.example.cmp_b.core.network.ApiService
import com.example.cmp_b.core.utils.NetworkResult
import com.example.cmp_b.shared.data.mapper.PostMapper
import com.example.cmp_b.shared.domain.model.Post
import com.example.cmp_b.shared.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostRepositoryImpl(
    private val apiService: ApiService,
    private val postMapper: PostMapper
) : PostRepository {
    
    override fun getPosts(): Flow<NetworkResult<List<Post>>> = flow {
        emit(NetworkResult.Loading)
        try {
            val postDtos = apiService.getPosts()
            val posts = postMapper.mapFromList(postDtos)
            emit(NetworkResult.Success(posts))
        } catch (e: Exception) {
            emit(NetworkResult.Error(e.message ?: "An unknown error occurred", e))
        }
    }

    override fun getPostById(id: Int): Flow<NetworkResult<Post>> = flow {
        emit(NetworkResult.Loading)
        try {
            val postDto = apiService.getPostById(id)
            emit(NetworkResult.Success(postMapper.mapFrom(postDto)))
        } catch (e: Exception) {
            emit(NetworkResult.Error(e.message ?: "An unknown error occurred", e))
        }
    }

    override fun createPost(post: Post): Flow<NetworkResult<Post>> = flow {
        emit(NetworkResult.Loading)
        try {
            val postDto = apiService.createPost(postMapper.mapToDto(post))
            emit(NetworkResult.Success(postMapper.mapFrom(postDto)))
        } catch (e: Exception) {
            emit(NetworkResult.Error(e.message ?: "An unknown error occurred", e))
        }
    }

    override fun updatePost(id: Int, post: Post): Flow<NetworkResult<Post>> = flow {
        emit(NetworkResult.Loading)
        try {
            val postDto = apiService.updatePost(id, postMapper.mapToDto(post))
            emit(NetworkResult.Success(postMapper.mapFrom(postDto)))
        } catch (e: Exception) {
            emit(NetworkResult.Error(e.message ?: "An unknown error occurred", e))
        }
    }

    override fun deletePost(id: Int): Flow<NetworkResult<Unit>> = flow {
        emit(NetworkResult.Loading)
        try {
            apiService.deletePost(id)
            emit(NetworkResult.Success(Unit))
        } catch (e: Exception) {
            emit(NetworkResult.Error(e.message ?: "An unknown error occurred", e))
        }
    }
}
