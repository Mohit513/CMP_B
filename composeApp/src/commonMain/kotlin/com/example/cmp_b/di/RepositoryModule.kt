package com.example.cmp_b.di

import com.example.cmp_b.data.remote.ApiService
import com.example.cmp_b.data.repository.PostRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { ApiService(get()) }
    single { PostRepository(get()) }
}
