package com.example.cmp_b.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module

expect val platformModule: Module

fun initKoin(
    appModule: Module = com.example.cmp_b.di.appModule,
    configure: KoinApplication.() -> Unit = {}
) = startKoin {
    modules(
        appModule,
        platformModule
    )
    configure()
}
