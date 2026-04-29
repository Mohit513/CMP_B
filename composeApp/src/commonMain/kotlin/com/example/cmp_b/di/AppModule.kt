package com.example.cmp_b.di

import com.example.cmp_b.ui.PostViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModule = module {
    viewModel { PostViewModel(get()) }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(networkModule, repositoryModule, appModule)
    }

fun appModules() = listOf(networkModule, repositoryModule, appModule)
