package com.example.cmp_b.core.data.network.remote

import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json

expect object HttpClientFactory {
    fun create(): HttpClient
}

internal fun createJson() = Json {
    ignoreUnknownKeys = true
    isLenient = true
    prettyPrint = true
}
