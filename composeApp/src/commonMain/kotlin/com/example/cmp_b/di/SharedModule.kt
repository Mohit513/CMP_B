package com.example.cmp_b.di

import com.example.cmp_b.shared.data.mapper.PostMapper
import com.example.cmp_b.shared.data.repository.PostRepositoryImpl
import com.example.cmp_b.shared.domain.repository.PostRepository
import com.example.cmp_b.shared.domain.usecase.GetPostsUseCase
import com.example.cmp_b.shared.presentation.viewmodel.PostListViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val sharedModule = module {
    // Mappers
    factoryOf(::PostMapper)

    // Repository - Bound to its interface
    singleOf(::PostRepositoryImpl) bind PostRepository::class
    
    // Use Cases - Defined as factory since they are stateless
    factoryOf(::GetPostsUseCase)
    
    // ViewModels - Defined as factory in commonMain for cross-platform support.
    // On Android, you can use koinViewModel() in Compose to get these.
    factoryOf(::PostListViewModel)
}


