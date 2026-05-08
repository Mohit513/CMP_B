package com.example.cmp_b.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cmp_b.composeapp.generated.resources.Res
import cmp_b.composeapp.generated.resources.ic_cloud_png
import cmp_b.composeapp.generated.resources.ic_eye_svg
import cmp_b.composeapp.generated.resources.ic_filebook_png
import cmp_b.composeapp.generated.resources.ic_fill_close_svg
import com.example.cmp_b.ui.theme.DoveGray
import com.example.cmp_b.ui.theme.Primary
import com.example.cmp_b.ui.theme.TextStyles
import com.example.cmp_b.ui.theme.Tundora
import com.example.cmp_b.ui.theme.Zumthor
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun AppUploadAadharField(
    titleText : String = "Upload Aadhaar Card",
    labelText: String,
    fileUri: String? = null,
    fileName: String? = null,
    fileSize: String? = null,
    onUploadClick: () -> Unit,
    onViewClick: () -> Unit = {},
    onRemoveClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
//
//        Text(
//            text = labelText,
//            style = TextStyles.InterMediumM,
//            color = Tundora
//        )

        Spacer(modifier = Modifier.height(8.dp))

        if (fileName == null) {

            // ---------------- EMPTY STATE ----------------
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Zumthor)
                    .dashedBorder(
                        color = Primary,
                        strokeWidth = 2.dp,
                        cornerRadius = 20.dp
                    )
                    .clickable { onUploadClick() }
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.CenterStart
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Image(
                        painter = painterResource(Res.drawable.ic_cloud_png),
                        contentDescription = null,
                        modifier = Modifier.size(28.dp)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {

                        Text(
                            text = titleText,
                            style = TextStyles.InterBoldM,
                            color = Primary
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Max File Size : 25MB",
                            style = TextStyles.InterRegularXS,
                            color = DoveGray
                        )
                    }
                }
            }

        } else {

            // ---------------- UPLOADED STATE ----------------
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .border(width = 2.dp, Primary, shape = RoundedCornerShape(20.dp))
                    .clip(RoundedCornerShape(20.dp))
                    .background(Zumthor)
                    .clickable { onUploadClick() }
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.CenterStart
            ) {

                Row(
                    modifier= Modifier.wrapContentSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        painter = painterResource(Res.drawable.ic_filebook_png),
                        contentDescription = null,
                        modifier = Modifier.size(38.dp)
                    )


                    Spacer(modifier = Modifier.width(12.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = fileName,
                            style = TextStyles.InterBoldM,
                            color = Primary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Uploaded File Size : $fileSize",
                            style = TextStyles.InterRegularXS,
                            color = Tundora
                        )
                    }

                    Row {

                        ActionIconCard(
                            icon = Res.drawable.ic_eye_svg,
                            onClick = onViewClick
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        ActionIconCard(
                            icon = Res.drawable.ic_fill_close_svg,
                            onClick = onRemoveClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ActionIconCard(
    icon: DrawableResource,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(44.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
    }
}
fun Modifier.dashedBorder(
    color: Color,
    strokeWidth: Dp,
    cornerRadius: Dp
) = this.then(
    Modifier.drawBehind {

        val stroke = strokeWidth.toPx()

        val dashEffect = PathEffect.dashPathEffect(
            floatArrayOf(12f, 12f),
            0f
        )

        drawRoundRect(
            color = color,
            size = size,
            cornerRadius = CornerRadius(
                cornerRadius.toPx(),
                cornerRadius.toPx()
            ),
            style = Stroke(
                width = stroke,
                pathEffect = dashEffect
            )
        )
    }
)