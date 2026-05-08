package com.example.cmp_b.util

import androidx.compose.runtime.Composable

interface FilePickerLauncher {
    fun launch(type: String)
    fun launch(mimeTypes: Array<String>)
    fun launch()
}

expect object FilePickerUtils {
    fun createTempImageUri(): String

    @Composable
    fun rememberGalleryLauncher(onImagePicked: (String?) -> Unit): FilePickerLauncher

    @Composable
    fun rememberDocumentLauncher(onFilePicked: (String?) -> Unit): FilePickerLauncher

    @Composable
    fun rememberCameraLauncher(onImageCaptured: (Boolean) -> Unit): FilePickerLauncher

    fun getFileName(uri: String): String
    fun getFileSize(uri: String): Long
    fun formatFileSize(size: Long): String
}
