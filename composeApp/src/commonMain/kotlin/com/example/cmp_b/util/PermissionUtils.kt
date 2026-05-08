package com.example.cmp_b.util

import androidx.compose.runtime.Composable

interface PermissionLauncher {
    fun launch(permission: String)
}

expect object PermissionUtils {
    fun hasCameraPermission(): Boolean

    @Composable
    fun rememberCameraPermissionLauncher(onResult: (Boolean) -> Unit): PermissionLauncher

    fun hasLocationPermission(): Boolean

    @Composable
    fun rememberLocationPermissionLauncher(onResult: (Boolean) -> Unit): PermissionLauncher

    fun hasMediaPermission(): Boolean

    @Composable
    fun rememberMediaPermissionLauncher(onResult: (Boolean) -> Unit): PermissionLauncher

    fun hasNotificationPermission(): Boolean

    @Composable
    fun rememberNotificationPermissionLauncher(onResult: (Boolean) -> Unit): PermissionLauncher

    @Composable
    fun rememberMultiplePermissionLauncher(onResult: (Map<String, Boolean>) -> Unit): PermissionLauncher
}
