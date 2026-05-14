package com.example.cmp_b.util


import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

object AppUtils {

    object DateFormats {
        const val ISO_DATE = "yyyy-MM-dd"
        const val DISPLAY_TIME = "h:mm a"
        const val API_TIME = "HH:mm:ss"
    }
}
expect class FileDownloader {
    fun downloadFile(
        fileName: String,
        url: String
    )
}



class AadharVisualTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {

        val trimmed = text.text.take(12)

        val formatted = trimmed.chunked(4).joinToString(" ")

        val offsetMapping = object : OffsetMapping {

            override fun originalToTransformed(offset: Int): Int {
                return when {
                    offset <= 4 -> offset
                    offset <= 8 -> offset + 1
                    offset <= 12 -> offset + 2
                    else -> 14
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when {
                    offset <= 4 -> offset
                    offset <= 9 -> offset - 1
                    offset <= 14 -> offset - 2
                    else -> 12
                }
            }
        }

        return TransformedText(
            AnnotatedString(formatted),
            offsetMapping
        )
    }


}