package com.example.cmp_b.core.data.network.interceptors

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttpConfig
import com.chuckerteam.chucker.api.ChuckerInterceptor
import android.content.Context

private var _appContext: Context? = null
internal val appContext: Context? get() = _appContext

fun setAppContext(context: Context) {
    _appContext = context
}

actual fun HttpClientConfig<*>.installInterceptor() {
    engine {
        if (this is OkHttpConfig) {
            _appContext?.let {
                addInterceptor(ChuckerInterceptor.Builder(it).build())
            }
        }
    }
}
