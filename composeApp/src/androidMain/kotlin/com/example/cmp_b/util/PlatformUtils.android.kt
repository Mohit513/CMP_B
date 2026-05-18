package com.example.cmp_b.util

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.example.cmp_b.core.data.network.interceptors.appContext

actual fun openAppSettings() {
    appContext?.let { context ->
        val intent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", context.packageName, null)
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}
