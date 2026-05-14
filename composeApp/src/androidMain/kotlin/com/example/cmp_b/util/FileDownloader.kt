package com.example.cmp_b.util

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri

actual class FileDownloader(
    private val context: Context
) {

    actual fun downloadFile(
        fileName: String,
        url: String
    ) {

        val request = DownloadManager.Request(url.toUri()).apply {

            setTitle(fileName)

            setDescription("Downloading file...")

            setNotificationVisibility(
                DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
            )

            setAllowedOverMetered(true)

            setAllowedOverRoaming(true)

            setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                fileName
            )
        }

        val downloadManager =
            context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        downloadManager.enqueue(request)
    }
}