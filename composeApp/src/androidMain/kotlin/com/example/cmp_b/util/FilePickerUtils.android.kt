package com.example.cmp_b.util

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.core.content.FileProvider
import com.example.cmp_b.core.data.network.interceptors.appContext
import java.io.File

actual object FilePickerUtils {
    actual fun createTempImageUri(): String {
        val context = appContext ?: return ""
        val directory = context.cacheDir
        val file = File.createTempFile("captured_image", ".jpg", directory)
        val uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file
        )
        return uri.toString()
    }

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
            override fun launch(type: String) { 
                try {
                    launcher.launch(Uri.parse(type))
                } catch (e: Exception) {
                    onImageCaptured(false)
                }
            }
            override fun launch(mimeTypes: Array<String>) {}
            override fun launch() {}
        }
    }

    actual fun getFileName(uri: String): String {
        return try {
            val context = appContext ?: return "image.jpg"
            val contentUri = Uri.parse(uri)
            if (contentUri.scheme == "content") {
                context.contentResolver.query(contentUri, null, null, null, null)?.use { cursor ->
                    if (cursor.moveToFirst()) {
                        val nameIndex = cursor.getColumnIndex(android.provider.OpenableColumns.DISPLAY_NAME)
                        if (nameIndex != -1) return cursor.getString(nameIndex)
                    }
                }
            }
            contentUri.path?.substringAfterLast('/') ?: "image.jpg"
        } catch (e: Exception) {
            "image.jpg"
        }
    }

    actual fun getFileSize(uri: String): Long {
        return try {
            val context = appContext ?: return 0
            val contentUri = Uri.parse(uri)
            if (contentUri.scheme == "content") {
                context.contentResolver.query(contentUri, null, null, null, null)?.use { cursor ->
                    if (cursor.moveToFirst()) {
                        val sizeIndex = cursor.getColumnIndex(android.provider.OpenableColumns.SIZE)
                        if (sizeIndex != -1) return cursor.getLong(sizeIndex)
                    }
                }
            }
            File(contentUri.path ?: "").length()
        } catch (e: Exception) {
            0
        }
    }

    actual fun formatFileSize(size: Long): String {
        if (size <= 0) return "0 B"
        val units = arrayOf("B", "KB", "MB", "GB", "TB")
        val digitGroups = (Math.log10(size.toDouble()) / Math.log10(1024.0)).toInt()
        return String.format("%.1f %s", size / Math.pow(1024.0, digitGroups.toDouble()), units[digitGroups])
    }
}
