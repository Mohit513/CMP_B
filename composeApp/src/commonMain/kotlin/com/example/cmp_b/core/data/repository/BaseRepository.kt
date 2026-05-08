package com.example.cmp_b.core.data.repository

import com.example.cmp_b.core.data.network.api.common.NetworkResult
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess

abstract class BaseRepository {
    suspend inline fun <reified T> safeApiCall(crossinline apiCall: suspend () -> HttpResponse): NetworkResult<T> {
        return try {
            val response = apiCall()
            if (response.status.isSuccess()) {
                NetworkResult.Success(response.body<T>())
            } else {
                val errorBody = try { response.bodyAsText() } catch (_: Exception) { null }
                val msg = buildString {
                    append("API Error ${response.status.value}: ${response.status.description}")
                    if (!errorBody.isNullOrBlank()) append(" | $errorBody")
                }
                NetworkResult.Error(msg)
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: "Unknown Error", e)
        }
    }
}