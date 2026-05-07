package com.example.cmp_b

interface PlatformInfo {
    val appVersion: String
    val buildNumber: String
    val osVersion: String
    val deviceModel: String
}

expect fun getPlatformInfo(): PlatformInfo
