package com.example.cmp_b.di

import com.example.cmp_b.PlatformInfo
import com.example.cmp_b.core.data.network.remote.ApiService
import com.example.cmp_b.core.data.network.remote.ApiServiceImpl
import com.example.cmp_b.core.data.network.remote.HttpClientFactory
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
