package com.example.cmp_b.ui.dashboard.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cmp_b.composeapp.generated.resources.Res
import cmp_b.composeapp.generated.resources.ic_calendar_svg
import cmp_b.composeapp.generated.resources.ic_calender_reg_svg
import cmp_b.composeapp.generated.resources.ic_document_details
import cmp_b.composeapp.generated.resources.png_payslip
import com.example.cmp_b.navigation.AppState
import com.example.cmp_b.ui.components.AppSimpleTopBar
import com.example.cmp_b.ui.theme.AppGradients
import com.example.cmp_b.ui.theme.Congress_blue
import com.example.cmp_b.ui.theme.DoveGray
import com.example.cmp_b.ui.theme.TextStyles
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun NotificationScreen(
    appState: AppState
) {
    val notifications = listOf(
        NotificationItem(
            id = "1",
            title = "Attendance Regularization",
            description = "Your attendance regularization for 20th Oct has been approved.",
            time = "10:30 AM",
            icon = Res.drawable.ic_calender_reg_svg
        ),
        NotificationItem(
            "2",
            "Leave Approved",
            "Your casual leave request for 25th Oct is approved by manager.",
            "Yesterday",
            Res.drawable.ic_calender_reg_svg
        ),
        NotificationItem(
            "3",
            "New Document Required",
            "Please upload your PAN card details in Document Details section.",
            "2 days ago",
            Res.drawable.ic_document_details
        ),
        NotificationItem(
            "4",
            "Holiday Update",
            "New holiday added: Diwali (31st Oct). Check holiday list for details.",
            "3 days ago",
            Res.drawable.ic_calendar_svg
        ),
        NotificationItem(
            "5",
            "Payslip Generated",
            "Your payslip for September 2024 is now available for download.",
            "5 days ago",
            Res.drawable.png_payslip
        )
    )

    Scaffold(
        topBar = {
            AppSimpleTopBar(
                title = "Notifications",
                onBackClick = { appState.navigator.popBack() }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(AppGradients.lightPrimaryBackground())
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(notifications) { item ->
                    NotificationCard(item)
                }
            }
        }
    }
}

@Composable
fun NotificationCard(item: NotificationItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Congress_blue.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(item.icon),
                    contentDescription = null,
                    tint = Congress_blue,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.title,
                        style = TextStyles.InterSemiBoldS,
                        color = Congress_blue,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = item.time,
                        style = TextStyles.InterRegularXXS,
                        color = DoveGray
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.description,
                    style = TextStyles.InterRegularXS,
                    color = Color.Black.copy(alpha = 0.7f),
                    lineHeight = 16.sp
                )
            }
        }
    }
}

data class NotificationItem(
    val id: String,
    val title: String,
    val description: String,
    val time: String,
    val icon: DrawableResource
)
