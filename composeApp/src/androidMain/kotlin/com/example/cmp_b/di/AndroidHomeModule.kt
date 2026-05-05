package com.example.cmp_b.di

import com.example.cmp_b.feature.home.presentation.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidHomeModule = module {
    viewModel { HomeViewModel(get()) }
}
