package com.example.cmp_b.core.network

import com.example.cmp_b.core.network.model.PostDto
import com.example.cmp_b.core.network.model.UserDto
import com.example.cmp_b.core.network.model.CommentDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.request.url

/**
 * Main API service interface following the pattern you requested
 * This uses Ktor client instead of Retrofit but follows the same annotation-style pattern
 */
interface ApiService {
    
    // Posts API methods
    suspend fun getPosts(): List<PostDto>
    suspend fun getPostById(id: Int): PostDto
    suspend fun createPost(post: PostDto): PostDto
    suspend fun updatePost(id: Int, post: PostDto): PostDto
    suspend fun deletePost(id: Int)
    
    // Users API methods
}

/**
 * Implementation of ApiService using Ktor client
 */
class ApiServiceImpl(
    private val httpClient: HttpClient
) : ApiService {
    
    // Posts API implementation
    override suspend fun getPosts(): List<PostDto> {
        return httpClient.get(ApiNames.GET_POSTS).body()
    }
    
    override suspend fun getPostById(id: Int): PostDto {
        return httpClient.get {
            url(ApiNames.GET_POST_BY_ID.replace("{id}", id.toString()))
        }.body()
    }
    
    override suspend fun createPost(post: PostDto): PostDto {
        return httpClient.post(ApiNames.CREATE_POST) {
            setBody(post)
        }.body()
    }
    
    override suspend fun updatePost(id: Int, post: PostDto): PostDto {
        return httpClient.put {
            url(ApiNames.UPDATE_POST.replace("{id}", id.toString()))
            setBody(post)
        }.body()
    }
    
    override suspend fun deletePost(id: Int) {
        httpClient.delete {
            url(ApiNames.DELETE_POST.replace("{id}", id.toString()))
        }
    }
}
