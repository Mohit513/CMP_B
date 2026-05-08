package com.example.cmp_b.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cmp_b.composeapp.generated.resources.Res
import cmp_b.composeapp.generated.resources.ic_call_svg
import cmp_b.composeapp.generated.resources.ic_chat_svg
import cmp_b.composeapp.generated.resources.ic_profile
import cmp_b.composeapp.generated.resources.ic_support_svg
import cmp_b.composeapp.generated.resources.image_top_login
import cmp_b.composeapp.generated.resources.img_banner
import cmp_b.composeapp.generated.resources.png_profile_cartoon
import com.example.cmp_b.ui.theme.AliceBlue_25
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import com.example.cmp_b.ui.theme.CreamBrulle
import com.example.cmp_b.ui.theme.TextStyles
import com.example.cmp_b.ui.theme.BackgroundLight
import com.example.cmp_b.ui.theme.Primary

data class DashboardItem(
    val title: String,
    val iconVector: DrawableResource,
    val route: String? = null
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppImageContentSlider(modifier: Modifier = Modifier) {
    val banners = listOf(
        Res.drawable.img_banner,
        Res.drawable.img_banner,
        Res.drawable.img_banner
    )

    val pagerState = rememberPagerState(pageCount = { banners.size })

    // Auto slide
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            val nextPage = (pagerState.currentPage + 1) % banners.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 0.dp) // Adjusted from 24.dp to match dashboard layout
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        ) { page ->
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 4.dp) // Added small padding between items
                    .border(width = 2.dp, shape = RoundedCornerShape(16.dp), color = BackgroundLight)
            ) {
                Image(
                    painter = painterResource(banners[page]),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun AuthHeaderImageWithProfile(
    title: String,
    subtitle: String,
    onProfileClick: () -> Unit,
    onMenuClick: () -> Unit,
    onNotificationClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
    ) {
        Image(
            painter = painterResource(Res.drawable.image_top_login),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.84f),
            contentScale = ContentScale.FillBounds
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(CreamBrulle)
                    .clickable(onClick = onProfileClick),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(Res.drawable.png_profile_cartoon),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = TextStyles.InterSemiBoldXL,
                    color = Color.White
                )
                Text(
                    text = subtitle,
                    style = TextStyles.InterRegularXS,
                    color = Color.White.copy(alpha = 0.85f)
                )
            }

            HeaderActionCleanButton(
                icon = Icons.Default.Notifications,
                onClick = onNotificationClick
            )

            Spacer(Modifier.width(10.dp))

            HeaderActionCleanButton(
                icon = Icons.Default.Menu,
                onClick = onMenuClick
            )
        }
    }
}

@Composable
fun HeaderActionCleanButton(
    icon: ImageVector,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSourceBottomSheet(
    onCallSelected: () -> Unit,
    onChatsSelected: () -> Unit,
    onSupportSelected: () -> Unit,
    onSelected: () -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = BackgroundLight
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 2.dp, vertical = 2.dp)

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
                    icon = Res.drawable.ic_call_svg,
                    title = "Call",
                    onClick = {
                        onCallSelected()
                        onDismiss()
                    }
                )

                HorizontalSourceItem(
                    icon = Res.drawable.ic_chat_svg,
                    title = "Chats",
                    onClick = {
                        onChatsSelected()
                        onDismiss()
                    }
                )

                HorizontalSourceItem(
                    icon = Res.drawable.ic_support_svg,
                    title = "Support",
                    onClick = {
                        onSupportSelected()
                        onDismiss()
                    }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun HorizontalSourceItem(
    icon: DrawableResource,
    title: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable { onClick() }
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(64.dp)
                .background(
                    color = AliceBlue_25,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
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
            text = title,
            style = TextStyles.InterRegularXS
        )
    }
}


