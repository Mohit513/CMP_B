package com.example.cmp_b.di

import com.example.cmp_b.feature.home.domain.mapper.HomePostMapper
import com.example.cmp_b.feature.home.domain.usecase.GetHomePostsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val homeModule = module {
    factoryOf(::HomePostMapper)
    factoryOf(::GetHomePostsUseCase)
}
