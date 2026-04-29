package com.example.cmp_b.data.remote

import com.example.cmp_b.data.model.Post
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

class ApiService(private val client: HttpClient) {
    suspend fun getPosts(): HttpResponse {
        return client.get(ApiNames.POSTS)
    }
}
