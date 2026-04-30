package com.example.cmp_b.di

import com.example.cmp_b.ui.PostViewModel
import com.example.cmp_b.ui.auth.LoginViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::PostViewModel)
    viewModelOf(::LoginViewModel)
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(networkModule, repositoryModule, appModule)
    }

fun appModules() = listOf(networkModule, repositoryModule, appModule)
