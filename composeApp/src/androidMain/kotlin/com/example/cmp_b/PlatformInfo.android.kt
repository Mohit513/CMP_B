package com.example.cmp_b

import android.os.Build
import com.example.cmp_b.BuildConfig

class AndroidPlatformInfo : PlatformInfo {
    override val appVersion: String
        get() = BuildConfig.VERSION_NAME

    override val buildNumber: String
        get() = BuildConfig.VERSION_CODE.toString()

    override val osVersion: String
        get() = Build.VERSION.RELEASE ?: "Unknown"

    override val deviceModel: String
        get() = "${Build.BRAND ?: "Unknown"} ${Build.MODEL ?: "Unknown"}"
}

actual fun getPlatformInfo(): PlatformInfo = AndroidPlatformInfo()
