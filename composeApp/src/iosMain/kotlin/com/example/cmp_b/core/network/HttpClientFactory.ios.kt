package com.example.cmp_b.core.network

import com.example.cmp_b.core.data.network.remote.ApiNames
import com.example.cmp_b.core.data.network.remote.createJson
import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.serialization.kotlinx.json.json
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.HttpHeaders

actual object HttpClientFactory {
    actual fun create(): HttpClient {
        return HttpClient(Darwin) {
            install(ContentNegotiation) {
                json(createJson())
            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }

            defaultRequest {
                url(ApiNames.BASE_URL)
                header(HttpHeaders.Accept, "application/json")
            }

            install(ResponseObserver) {
                onResponse { response ->
                    // iOS specific "interceptor" for logging or monitoring
                    println("iOS Response: ${response.status.value} ${response.call.request.url}")
                }
            }
        }
    }
}
