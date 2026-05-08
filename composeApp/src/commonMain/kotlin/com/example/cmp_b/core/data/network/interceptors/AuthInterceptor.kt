package com.example.cmp_b.core.data.network.interceptors

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header

class AuthInterceptor {
    fun intercept(request: HttpRequestBuilder) {
        // Add authentication headers here if needed
        // request.header("Authorization", "Bearer $token")
    }
}
