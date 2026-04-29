package com.example.cmp_b.util

import io.ktor.client.HttpClientConfig

actual fun HttpClientConfig<*>.installInterceptor() {
    // iOS relies on Ktor's Logging feature which prints to the Xcode console.
    // There is no direct Chucker equivalent for iOS that works in-app like Android.
}
