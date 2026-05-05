package com.example.cmp_b.core.network

import com.chuckerteam.chucker.api.ChuckerInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import android.content.Context
import io.ktor.client.plugins.defaultRequest

actual object HttpClientFactory : KoinComponent {
    
    private fun createJson() = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }
    actual fun create(): HttpClient {
        val context: Context = get()
        return HttpClient(OkHttp) {
            engine {
                addInterceptor(ChuckerInterceptor.Builder(context).build())
            }
            
            install(ContentNegotiation) {
                json(createJson())
            }
            
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.INFO
            }
            
            defaultRequest {
                url(ApiNames.BASE_URL)
            }
        }
    }
}
