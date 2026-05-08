package com.example.cmp_b.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cmp_b.composeapp.generated.resources.Res
import cmp_b.composeapp.generated.resources.ic_arrow_back
import com.example.cmp_b.ui.theme.Azure
import com.example.cmp_b.ui.theme.TextStyles
import org.jetbrains.compose.resources.painterResource

@Composable
fun AppSimpleTopBar(
    modifier: Modifier = Modifier,
    title: String,
    backgroundColor: Color = Azure,
    showBackButton: Boolean = true,
    onBackClick: (() -> Unit)? = null,
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(backgroundColor)
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .padding(horizontal = 0.dp)
                .padding(top = 14.dp),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = title,
                color = Color.White,
                style = TextStyles.InterSemiBoldL
            )

            if (showBackButton) {
                IconButton(
                    onClick = { onBackClick?.invoke() },
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_arrow_back),
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }
        }
    }
}