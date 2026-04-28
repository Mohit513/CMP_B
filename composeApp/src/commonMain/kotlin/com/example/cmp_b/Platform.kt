package com.example.cmp_b

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform