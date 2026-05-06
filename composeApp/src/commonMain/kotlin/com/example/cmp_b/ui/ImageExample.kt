package com.example.cmp_b.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cmp_b.composeapp.generated.resources.Res
import cmp_b.composeapp.generated.resources.sample_icon
import org.jetbrains.compose.resources.painterResource
// Note: These imports are generated after building the project.
// If you see red text, please run 'Sync Gradle' or 'Build' the project.

@Composable
fun ImageExample() {
    Column {
        // Example using the sample SVG we created
        Image(
            painter = painterResource(Res.drawable.sample_icon),
            contentDescription = "Sample SVG Icon",
            modifier = Modifier.size(100.dp)
        )

        // Guide for PNG:
        // 1. Place your 'my_image.png' in composeApp/src/commonMain/composeResources/drawable/
        // 2. Access it using:
        // Image(painter = painterResource(Res.drawable.my_image), contentDescription = null)
    }
}
