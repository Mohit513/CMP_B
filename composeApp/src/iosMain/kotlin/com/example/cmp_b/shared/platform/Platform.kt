package com.example.cmp_b.shared.platform

import platform.UIKit.UIDevice

actual class Platform {
    actual val platform: String = "iOS ${UIDevice.currentDevice.systemVersion}"
}
