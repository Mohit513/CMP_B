package com.example.cmp_b.util

import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationOpenSettingsURLString

actual fun openAppSettings() {
    val url = NSURL(string = UIApplicationOpenSettingsURLString)
    if (UIApplication.sharedApplication.canOpenURL(url)) {
        UIApplication.sharedApplication.openURL(url)
    }
}
