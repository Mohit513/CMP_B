package com.example.cmp_b.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.cmp_b.ui.theme.BackgroundLight
import com.example.cmp_b.ui.theme.TextStyles
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun AuthHeaderImage(
    imageRes: DrawableResource,
    title: String,
    subtitleContent: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxWidth().height(300.dp)
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier.align(Alignment.TopStart)
                .padding(horizontal = 24.dp, vertical = 80.dp)
        ) {
            Text(
                text = title,
                style = TextStyles.InterSemiBoldXXL,
                color = BackgroundLight,
            )
            Spacer(modifier = Modifier.height(8.dp))
            subtitleContent()
        }
    }
}





