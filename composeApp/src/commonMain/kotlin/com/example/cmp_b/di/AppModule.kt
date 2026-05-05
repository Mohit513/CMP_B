package com.example.cmp_b.di

import org.koin.dsl.module

val appModule = module {
    includes(
        coreModule,
        sharedModule,
        homeModule,
        navigationModule
    )
}
