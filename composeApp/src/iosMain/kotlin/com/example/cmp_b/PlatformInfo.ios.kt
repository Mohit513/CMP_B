package com.example.cmp_b

import platform.Foundation.NSBundle
import platform.UIKit.UIDevice

class IOSPlatformInfo : PlatformInfo {
    override val appVersion: String
        get() = NSBundle.mainBundle.infoDictionary?.get("CFBundleShortVersionString") as? String ?: "1.0.0"

    override val buildNumber: String
        get() = NSBundle.mainBundle.infoDictionary?.get("CFBundleVersion") as? String ?: "1"

    override val osVersion: String
        get() = UIDevice.currentDevice.systemVersion

    override val deviceModel: String
        get() = UIDevice.currentDevice.model
}

actual fun getPlatformInfo(): PlatformInfo = IOSPlatformInfo()
