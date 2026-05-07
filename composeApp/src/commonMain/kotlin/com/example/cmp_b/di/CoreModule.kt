package com.example.cmp_b.di

import com.example.cmp_b.PlatformInfo
import com.example.cmp_b.core.network.ApiService
import com.example.cmp_b.core.network.ApiServiceImpl
import com.example.cmp_b.core.network.HttpClientFactory
import com.example.cmp_b.getPlatformInfo
import io.ktor.client.HttpClient
import org.koin.dsl.module

val coreModule = module {
    single<HttpClient> { HttpClientFactory.create() }

    // Main API service - simplified structure
    single<ApiService> { ApiServiceImpl(get()) }

    // Platform info for device/OS metadata
    single<PlatformInfo> { getPlatformInfo() }
}
