package com.example.cmp_b.di

import com.example.cmp_b.core.navigation.NavigationDestination
import com.example.cmp_b.core.navigation.NavigationManager
import com.example.cmp_b.core.navigation.NavController
import com.example.cmp_b.core.navigation.destinations.*
import org.koin.dsl.module

val navigationModule = module {
    
    // Navigation destinations
    single<List<NavigationDestination>> {
        listOf(
            HomeDestination,
            ProfileDestination,
            SettingsDestination,
            PostDetailDestination
        )
    }
    
    // Navigation manager
    single { NavigationManager() }
    
    // Navigation controller
    single { NavController(get(), get()) }
}
