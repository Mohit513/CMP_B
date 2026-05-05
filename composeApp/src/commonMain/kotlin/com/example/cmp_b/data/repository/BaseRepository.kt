package com.example.cmp_b.data.repository

import com.example.cmp_b.core.utils.NetworkResult
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess

abstract class BaseRepository {
    suspend inline fun <reified T> safeApiCall(crossinline apiCall: suspend () -> HttpResponse): NetworkResult<T> {
        return try {
            val response = apiCall()
            if (response.status.isSuccess()) {
                NetworkResult.Success(response.body<T>())
            } else {
                NetworkResult.Error("API Error: ${response.status.value} ${response.status.description}")
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: "Unknown Error", e)
        }
    }
}
