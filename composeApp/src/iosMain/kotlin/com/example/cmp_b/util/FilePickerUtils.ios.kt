package com.example.cmp_b.util

import androidx.compose.runtime.Composable

actual object FilePickerUtils {
    actual fun createTempImageUri(): String = ""

    @Composable
    actual fun rememberGalleryLauncher(onImagePicked: (String?) -> Unit): FilePickerLauncher {
        return object : FilePickerLauncher {
            override fun launch(type: String) {}
            override fun launch(mimeTypes: Array<String>) {}
            override fun launch() {}
        }
    }

    @Composable
    actual fun rememberDocumentLauncher(onFilePicked: (String?) -> Unit): FilePickerLauncher {
        return object : FilePickerLauncher {
            override fun launch(type: String) {}
            override fun launch(mimeTypes: Array<String>) {}
            override fun launch() {}
        }
    }

    @Composable
    actual fun rememberCameraLauncher(onImageCaptured: (Boolean) -> Unit): FilePickerLauncher {
        return object : FilePickerLauncher {
            override fun launch(type: String) {}
            override fun launch(mimeTypes: Array<String>) {}
            override fun launch() {}
        }
    }

    actual fun getFileName(uri: String): String = ""
    actual fun getFileSize(uri: String): Long = 0
    actual fun formatFileSize(size: Long): String = ""
}
