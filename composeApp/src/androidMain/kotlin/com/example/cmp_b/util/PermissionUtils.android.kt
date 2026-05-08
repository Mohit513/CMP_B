package com.example.cmp_b.util

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

actual object PermissionUtils {
    actual fun hasCameraPermission(): Boolean = true

    @Composable
    actual fun rememberCameraPermissionLauncher(onResult: (Boolean) -> Unit): PermissionLauncher {
        val launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {
            onResult(it)
        }
        return object : PermissionLauncher {
            override fun launch(permission: String) {
                launcher.launch(permission)
            }
        }
    }

    actual fun hasLocationPermission(): Boolean = true
    @Composable
    actual fun rememberLocationPermissionLauncher(onResult: (Boolean) -> Unit): PermissionLauncher = object : PermissionLauncher { override fun launch(permission: String) {} }
    actual fun hasMediaPermission(): Boolean = true
    @Composable
    actual fun rememberMediaPermissionLauncher(onResult: (Boolean) -> Unit): PermissionLauncher = object : PermissionLauncher { override fun launch(permission: String) {} }
    actual fun hasNotificationPermission(): Boolean = true
    @Composable
    actual fun rememberNotificationPermissionLauncher(onResult: (Boolean) -> Unit): PermissionLauncher = object : PermissionLauncher { override fun launch(permission: String) {} }
    @Composable
    actual fun rememberMultiplePermissionLauncher(onResult: (Map<String, Boolean>) -> Unit): PermissionLauncher = object : PermissionLauncher { override fun launch(permission: String) {} }
}
