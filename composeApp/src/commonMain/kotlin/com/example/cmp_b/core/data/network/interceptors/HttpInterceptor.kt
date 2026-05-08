package com.example.cmp_b.core.data.network.interceptors

import io.ktor.client.HttpClientConfig

expect fun HttpClientConfig<*>.installInterceptor()
