package com.example.cmp_b.ui.dashboard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cmp_b.composeapp.generated.resources.Res
import cmp_b.composeapp.generated.resources.innov_id_card_png
import cmp_b.composeapp.generated.resources.png_attendance_calender
import cmp_b.composeapp.generated.resources.png_candidate_details
import cmp_b.composeapp.generated.resources.png_check
import cmp_b.composeapp.generated.resources.png_civil_score
import cmp_b.composeapp.generated.resources.png_document_details
import cmp_b.composeapp.generated.resources.png_education_details
import cmp_b.composeapp.generated.resources.png_esic
import cmp_b.composeapp.generated.resources.png_family_details
import cmp_b.composeapp.generated.resources.png_leave
import cmp_b.composeapp.generated.resources.png_myletters
import cmp_b.composeapp.generated.resources.png_onboarding
import cmp_b.composeapp.generated.resources.png_payslip
import cmp_b.composeapp.generated.resources.png_pf_epf
import cmp_b.composeapp.generated.resources.png_profile
import cmp_b.composeapp.generated.resources.png_referal
import cmp_b.composeapp.generated.resources.png_refernce_details
import cmp_b.composeapp.generated.resources.png_reimbursment
import cmp_b.composeapp.generated.resources.png_timesheet
import cmp_b.composeapp.generated.resources.png_workexperince
import com.example.cmp_b.navigation.AppState
import com.example.cmp_b.navigation.NavRoutes
import com.example.cmp_b.navigation.NavigationEvent
import com.example.cmp_b.ui.components.AuthHeaderImageWithProfile
import com.example.cmp_b.ui.components.DashboardItem
import com.example.cmp_b.ui.components.AppImageContentSlider
import com.example.cmp_b.ui.components.AppSourceBottomSheet
import com.example.cmp_b.ui.theme.AppGradients
import com.example.cmp_b.ui.theme.Alabaster
import com.example.cmp_b.ui.theme.Congress_blue
import com.example.cmp_b.ui.theme.CreamBrulle
import com.example.cmp_b.ui.theme.DoveGray
import com.example.cmp_b.ui.theme.GalleryColor
import com.example.cmp_b.ui.theme.Havelock_blue
import com.example.cmp_b.ui.theme.Piper
import com.example.cmp_b.ui.theme.SeaSellPeach
import com.example.cmp_b.ui.theme.TextStyles
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DigiDashboardScreen(
    appState: AppState,
    viewModel: DashboardViewModel = koinViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState().value
    var showBottomSheet by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.navigation.collect { event ->
            when (event) {
                is NavigationEvent.Navigate -> {
                    appState.navigator.navigate(event.route)
                }
                is NavigationEvent.PopBack -> {
                    appState.navigator.popBack()
                }
                is NavigationEvent.ClearBackStackAndNavigate -> {
                    appState.navigator.clearAndNavigate(event.route)
                }
                is NavigationEvent.PopBackWithResult -> {
                    appState.navigator.popBackWithResult(key = event.key, value = event.value)
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadDashboardItems(dashboardItems)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppGradients.lightPrimaryBackground(Havelock_blue))
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = 180.dp, bottom = 24.dp)
        ) {
            item {
                AppImageContentSlider(modifier = Modifier.padding(top = 8.dp))
                Spacer(Modifier.height(12.dp))
                CompleteProfileCard(onClick = {})
                Spacer(Modifier.height(12.dp))
            }

            gridItems(
                items = uiState.dashboardItems,
                columns = 3
            ) { item ->
                DashboardMenuItem(
                    item = item,
                    onClick = {
                        viewModel.onEvent(
                            DashboardEvent.OnMenuClick(item)
                        )
                    }
                )
            }
        }

        AuthHeaderImageWithProfile(
            title = "Welcome ${uiState.userName} 👋",
            subtitle = "Great to see you here",
            onProfileClick = { viewModel.onEvent(DashboardEvent.OnProfileClick) },
            onMenuClick = { showBottomSheet = true },
            onNotificationClick = { viewModel.onEvent(DashboardEvent.OnNotificationClick) }
        )
    }

    if (showBottomSheet) {
        AppSourceBottomSheet(
            onCallSelected = { showBottomSheet = false },
            onChatsSelected = { showBottomSheet = false },
            onSupportSelected = {},
            onSelected = {},
            onDismiss = { showBottomSheet = false }
        )
    }
}

@Composable
private fun CompleteProfileCard(onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 2.dp)
            .clickable { onClick() }
            .border(width = 1.dp, color = Piper, shape = RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .background(SeaSellPeach)
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp),
            verticalAlignment = Alignment.Top
        ) {
            ProfileProgressAvatar(
                progress = 0.82f,
                percentageText = "82%",
                modifier = Modifier.padding(top = 10.dp)
            )
            Spacer(Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 10.dp)
            ) {
                Text(
                    text = "Complete your profile",
                    style = TextStyles.InterSemiBoldM
                )
                Text(
                    text = "We need few more details to complete your profile.\nPlease fill the remaining information.",
                    style = TextStyles.InterRegularXS,
                    color = DoveGray,
                    lineHeight = 14.sp
                )
            }
            IconButton(
                onClick = onClick,
                modifier = Modifier.align(Alignment.Top)
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Complete profile",
                    tint = Color.Unspecified
                )
            }
        }
    }
}

@Composable
fun ProfileProgressAvatar(
    progress: Float = 0.82f,
    percentageText: String = "82%",
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.size(46.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val strokeWidth = 3.dp.toPx()
            drawArc(
                color = Color(0xFFE8E0D9),
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(strokeWidth, cap = StrokeCap.Round)
            )
            drawArc(
                color = Color(0xFFB86A2B),
                startAngle = -90f,
                sweepAngle = 360 * progress,
                useCenter = false,
                style = Stroke(strokeWidth, cap = StrokeCap.Round)
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .background(color = CreamBrulle)
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = Congress_blue,
                modifier = Modifier.size(24.dp)
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = 10.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(GalleryColor)
                .padding(horizontal = 8.dp, vertical = 2.dp)
        ) {
            Text(
                text = percentageText,
                color = Color(0xFFB86A2B),
                style = TextStyles.InterMediumXXS
            )
        }
    }
}

@Composable
fun DashboardMenuItem(item: DashboardItem, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(100.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(bounded = true, radius = 20.dp)
                ) {
                    onClick()
                }
        ) {
            Canvas(
                modifier = Modifier
                    .size(97.dp)
                    .align(Alignment.BottomCenter)
            ) {
                drawArc(
                    color = Congress_blue,
                    startAngle = 0f,
                    sweepAngle = 180f,
                    useCenter = true
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(98.dp)
                    .shadow(8.dp, CircleShape)
                    .clip(CircleShape)
                    .background(Alabaster)
            ) {
                Image(
                    painter = painterResource(resource = item.iconVector),
                    contentDescription = item.title,
                    modifier = Modifier.size(46.dp),
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = item.title,
            style = TextStyles.InterMediumXS,
            textAlign = TextAlign.Center
        )
    }
}

fun <T> LazyListScope.gridItems(
    items: List<T>,
    columns: Int,
    itemContent: @Composable (T) -> Unit,
) {
    val rows = items.chunked(columns)
    items(rows) { rowItems ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            rowItems.forEach { item ->
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    itemContent(item)
                }
            }
            if (rowItems.size < columns) {
                repeat(columns - rowItems.size) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}

private val dashboardItems = listOf(
    DashboardItem("Onboarding", Res.drawable.png_onboarding, NavRoutes.DigiOnBoarding.route),
    DashboardItem("Profile", Res.drawable.png_profile, NavRoutes.ProfileScreen.route),
    DashboardItem("My Letters", Res.drawable.png_myletters, NavRoutes.MyLettersScreen.route),
    DashboardItem("Attendance", Res.drawable.png_check, NavRoutes.AttendanceScreen.route),
    DashboardItem("Calendar", Res.drawable.png_attendance_calender, NavRoutes.DummyScreen.route),
    DashboardItem("Payslip", Res.drawable.png_payslip, NavRoutes.PayslipScreen.route),
    DashboardItem("PF/ESIC/Insurance", Res.drawable.png_pf_epf, NavRoutes.PfListScreen.route),
    DashboardItem("Leave", Res.drawable.png_leave,),
    DashboardItem("Reimbursement", Res.drawable.png_reimbursment, NavRoutes.ReimbursementScreen.route),
    DashboardItem("Candidate Details", Res.drawable.png_candidate_details),
    DashboardItem("Cibil Score", Res.drawable.png_civil_score),
    DashboardItem("Family Details", Res.drawable.png_family_details),
    DashboardItem("Education Details", Res.drawable.png_education_details),
    DashboardItem("Work Experience", Res.drawable.png_workexperince),
    DashboardItem("ESIC Details", Res.drawable.png_esic),
    DashboardItem("Document Details", Res.drawable.png_document_details, NavRoutes.DocumentDetailsScreen.route),
    DashboardItem("References Details", Res.drawable.png_refernce_details),
    DashboardItem("Time Sheet", Res.drawable.png_timesheet),
    DashboardItem("Referall", Res.drawable.png_referal),
    DashboardItem("Attendance Calender", Res.drawable.png_attendance_calender),
    DashboardItem("Innov Id Card", Res.drawable.innov_id_card_png, NavRoutes.IdCardScreen.route)
)
