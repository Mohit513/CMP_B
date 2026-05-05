package com.example.cmp_b

import androidx.compose.ui.window.ComposeUIViewController
import com.example.cmp_b.di.initKoin

fun MainViewController() = ComposeUIViewController { App() }

fun initKoinIos() {
    initKoin()
}
