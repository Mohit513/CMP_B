package com.example.cmp_b.di

import android.content.Context
import org.koin.dsl.module

val androidPlatformModule = module {
    // Context is automatically provided by androidContext() in MainActivity
}

actual val platformModule = androidPlatformModule
