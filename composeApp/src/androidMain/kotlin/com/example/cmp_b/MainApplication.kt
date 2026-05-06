package com.example.cmp_b

import android.app.Application
import com.example.cmp_b.di.initKoin
import com.example.cmp_b.util.setAppContext
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setAppContext(this)
        initKoin {
            androidLogger()
            androidContext(this@MainApplication)
        }
    }
}
