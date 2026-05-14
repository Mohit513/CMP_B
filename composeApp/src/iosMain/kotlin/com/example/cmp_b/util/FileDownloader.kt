package com.example.cmp_b.util

import platform.Foundation.NSURL
import platform.Foundation.NSURLSession
import platform.Foundation.NSURLRequest
import platform.Foundation.downloadTaskWithRequest

actual class FileDownloader {

    actual fun downloadFile(
        fileName: String,
        url: String
    ) {

        val nsUrl = NSURL.URLWithString(url)

        val request = NSURLRequest.requestWithURL(nsUrl!!)

        val task = NSURLSession.sharedSession
            .downloadTaskWithRequest(request) { localUrl, _, error ->

                if (error != null) {
                    println("Download failed")
                } else {
                    println("File downloaded")
                }
            }

        task.resume()
    }
}