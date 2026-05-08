package com.example.cmp_b.util

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

actual object FilePickerUtils {
    actual fun createTempImageUri(): String = ""

    @Composable
    actual fun rememberGalleryLauncher(onImagePicked: (String?) -> Unit): FilePickerLauncher {
        val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            onImagePicked(uri?.toString())
        }
        return object : FilePickerLauncher {
            override fun launch(type: String) { launcher.launch(type) }
            override fun launch(mimeTypes: Array<String>) {}
            override fun launch() { launcher.launch("image/*") }
        }
    }

    @Composable
    actual fun rememberDocumentLauncher(onFilePicked: (String?) -> Unit): FilePickerLauncher {
        val launcher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
            onFilePicked(uri?.toString())
        }
        return object : FilePickerLauncher {
            override fun launch(type: String) {}
            override fun launch(mimeTypes: Array<String>) { launcher.launch(mimeTypes) }
            override fun launch() { launcher.launch(arrayOf("*/*")) }
        }
    }

    @Composable
    actual fun rememberCameraLauncher(onImageCaptured: (Boolean) -> Unit): FilePickerLauncher {
        val launcher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            onImageCaptured(success)
        }
        return object : FilePickerLauncher {
            override fun launch(type: String) { launcher.launch(Uri.parse(type)) }
            override fun launch(mimeTypes: Array<String>) {}
            override fun launch() {}
        }
    }

    actual fun getFileName(uri: String): String = ""
    actual fun getFileSize(uri: String): Long = 0
    actual fun formatFileSize(size: Long): String = ""
}
