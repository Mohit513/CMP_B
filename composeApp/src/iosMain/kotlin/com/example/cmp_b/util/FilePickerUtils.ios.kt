package com.example.cmp_b.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import platform.UIKit.*
import platform.darwin.NSObject

actual object FilePickerUtils {
    actual fun createTempImageUri(): String = ""

    @Composable
    actual fun rememberGalleryLauncher(onImagePicked: (String?) -> Unit): FilePickerLauncher {
        val imagePicker = UIImagePickerController()
        val delegate = remember {
            object : NSObject(), UIImagePickerControllerDelegateProtocol, UINavigationControllerDelegateProtocol {
                override fun imagePickerController(picker: UIImagePickerController, didFinishPickingMediaWithInfo: Map<Any?, *>) {
                    // In a real app, you would save the image to a temporary file and return the path
                    onImagePicked("picked_image_uri") 
                    picker.dismissViewControllerAnimated(true, null)
                }
                override fun imagePickerControllerDidCancel(picker: UIImagePickerController) {
                    picker.dismissViewControllerAnimated(true, null)
                }
            }
        }

        return object : FilePickerLauncher {
            override fun launch(type: String) {
                imagePicker.sourceType = UIImagePickerControllerSourceType.UIImagePickerControllerSourceTypePhotoLibrary
                imagePicker.delegate = delegate
                UIApplication.sharedApplication.keyWindow?.rootViewController?.presentViewController(imagePicker, true, null)
            }
            override fun launch(mimeTypes: Array<String>) {}
            override fun launch() { launch("image/*") }
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
        val imagePicker = UIImagePickerController()
        val delegate = remember {
            object : NSObject(), UIImagePickerControllerDelegateProtocol, UINavigationControllerDelegateProtocol {
                override fun imagePickerController(picker: UIImagePickerController, didFinishPickingMediaWithInfo: Map<Any?, *>) {
                    onImageCaptured(true)
                    picker.dismissViewControllerAnimated(true, null)
                }
                override fun imagePickerControllerDidCancel(picker: UIImagePickerController) {
                    onImageCaptured(false)
                    picker.dismissViewControllerAnimated(true, null)
                }
            }
        }

        return object : FilePickerLauncher {
            override fun launch(type: String) {
                if (UIImagePickerController.isSourceTypeAvailable(UIImagePickerControllerSourceType.UIImagePickerControllerSourceTypeCamera)) {
                    imagePicker.sourceType = UIImagePickerControllerSourceType.UIImagePickerControllerSourceTypeCamera
                    imagePicker.delegate = delegate
                    UIApplication.sharedApplication.keyWindow?.rootViewController?.presentViewController(imagePicker, true, null)
                } else {
                    onImageCaptured(false)
                }
            }
            override fun launch(mimeTypes: Array<String>) {}
            override fun launch() {}
        }
    }

    actual fun getFileName(uri: String): String = "image.jpg"
    actual fun getFileSize(uri: String): Long = 0
    actual fun formatFileSize(size: Long): String = "0 B"
}
