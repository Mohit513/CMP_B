package com.example.cmp_b.core.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

expect object HttpClientFactory {
    fun create(): HttpClient
}

internal fun createJson() = Json {
    ignoreUnknownKeys = true
    isLenient = true
    prettyPrint = true
}
