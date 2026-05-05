package com.example.cmp_b.util

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttpConfig
import com.chuckerteam.chucker.api.ChuckerInterceptor
import android.content.Context

private var appContext: Context? = null

fun setAppContext(context: Context) {
    appContext = context
}

actual fun HttpClientConfig<*>.installInterceptor() {
    engine {
        if (this is OkHttpConfig) {
            appContext?.let {
                addInterceptor(ChuckerInterceptor.Builder(it).build())
            }
        }
    }
}
