package com.example.cmp_b.shared.platform

actual class Platform {
    actual val platform: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}
