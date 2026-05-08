package com.example.cmp_b.ui.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cmp_b.composeapp.generated.resources.Res
import cmp_b.composeapp.generated.resources.ic_call_svg
import cmp_b.composeapp.generated.resources.ic_camera_svg
import cmp_b.composeapp.generated.resources.ic_chat_svg
import cmp_b.composeapp.generated.resources.ic_files_svg
import cmp_b.composeapp.generated.resources.ic_gallery_svg
import cmp_b.composeapp.generated.resources.ic_support_svg
import com.example.cmp_b.ui.theme.AliceBlue_25
import com.example.cmp_b.ui.theme.BackgroundLight
import com.example.cmp_b.ui.theme.Primary
import com.example.cmp_b.ui.theme.TextStyles
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageSourceBottomSheet(
    onCameraSelected: () -> Unit,
    onGallerySelected: () -> Unit,
    onFileSelected: () -> Unit,
    onDismiss: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss, sheetState = sheetState, containerColor = BackgroundLight
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp, vertical = 2.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Select File Source",
                style = TextStyles.InterSemiBoldM,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 2.dp)
                    .padding(bottom = 12.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                HorizontalSourceItem(
                    icon = Res.drawable.ic_camera_svg, title = "Camera", onClick = {
                        onCameraSelected()
                        onDismiss()
                    })

                HorizontalSourceItem(
                    icon = Res.drawable.ic_gallery_svg, title = "Gallery", onClick = {
                        onGallerySelected()
                        onDismiss()
                    })

                HorizontalSourceItem(
                    icon = Res.drawable.ic_files_svg, title = "Files", onClick = {
                        onFileSelected()
                        onDismiss()
                    })
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSourceBottomSheet(
    onCallSelected: () -> Unit,
    onChatsSelected: () -> Unit,
    onSupportSelected: () -> Unit,
    onSelected: () -> Unit,
    onDismiss: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss, sheetState = sheetState, containerColor = BackgroundLight
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp, vertical = 2.dp)

        ) {
//             Image(painter = painterResource(R.drawable.ic_fill_close_svg), contentDescription = "close"
//             , modifier = Modifier.align(Alignment.End)
//                     .padding(horizontal = 16.dp)
//                     .size(38.dp))
//            Text(text = "Select source", style = MaterialTheme.typography.titleMedium,
//                modifier = Modifier.padding(horizontal = 24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                HorizontalSourceItem(
                    icon = Res.drawable.ic_call_svg, title = "Call", onClick = {
                        onCallSelected()
                        onDismiss()
                    })

                HorizontalSourceItem(
                    icon = Res.drawable.ic_chat_svg, title = "Chats", onClick = {
                        onChatsSelected()
                        onDismiss()
                    })

                HorizontalSourceItem(
                    icon = Res.drawable.ic_support_svg, title = "Support", onClick = {
                        onSupportSelected()
                        onDismiss()
                    })
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun HorizontalSourceItem(
    icon: DrawableResource,
    title: String,
    onClick: () -> Unit,
) {
    Column(modifier = Modifier.clickable { onClick() }.padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Box(
            modifier = Modifier.size(64.dp).background(
                    color = AliceBlue_25, shape = CircleShape
                ), contentAlignment = Alignment.Center
        ) {

            Icon(
                painter = painterResource(icon),
                contentDescription = title,
                tint = Primary,
                modifier = Modifier.size(28.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = title, style = TextStyles.InterRegularXS
        )
    }
}