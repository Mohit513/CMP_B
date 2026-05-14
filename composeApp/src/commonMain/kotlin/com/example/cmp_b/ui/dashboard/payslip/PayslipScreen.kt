package com.example.cmp_b.ui.dashboard.payslip

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cmp_b.composeapp.generated.resources.Res
import cmp_b.composeapp.generated.resources.ic_download_svg
import com.example.cmp_b.navigation.AppState
import com.example.cmp_b.ui.components.AppSimpleTopBar
import com.example.cmp_b.ui.theme.AliceBlue_25
import com.example.cmp_b.ui.theme.Alto_5
import com.example.cmp_b.ui.theme.Azure
import com.example.cmp_b.ui.theme.BackgroundLight
import com.example.cmp_b.ui.theme.CatskillWhite
import com.example.cmp_b.ui.theme.Congress_blue
import com.example.cmp_b.ui.theme.DustyGray
import com.example.cmp_b.ui.theme.Silver
import com.example.cmp_b.ui.theme.TextStyles
import com.example.cmp_b.ui.dashboard.payslip.vm.MonthStatus
import com.example.cmp_b.ui.dashboard.payslip.vm.PayslipEvent
import com.example.cmp_b.ui.dashboard.payslip.vm.PayslipViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PayslipScreen(
    appState: AppState,
    viewModel: PayslipViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            AppSimpleTopBar(
                title = "Payslip", onBackClick = { appState.navController.popBackStack() })
        }) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).background(CatskillWhite)
        ) {
            // Year Selector
            LazyRow(
                modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(uiState.years) { year ->
                    YearChip(
                        year = year,
                        isSelected = year == uiState.selectedYear,
                        onClick = { viewModel.onEvent(PayslipEvent.OnYearClick(year)) })
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(width = 0.6.dp, Silver),
                colors = CardDefaults.cardColors(containerColor = BackgroundLight),
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier.width(4.dp).height(24.dp)
                                .clip(RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp))
                                .background(Azure)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Download all payslip for year ${uiState.selectedYear}",
                            style = TextStyles.InterSemiBoldS,
                        )
                    }

                    HorizontalDivider(color = CatskillWhite, thickness = 1.dp)

                    LazyColumn {
                        itemsIndexed(uiState.months) { index, monthStatus ->
                            MonthItem(
                                index = index, monthStatus = monthStatus, onDownloadClick = {
                                    viewModel.onEvent(
                                        PayslipEvent.OnDownloadClick(monthStatus.month)
                                    )
                                })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun YearChip(
    year: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier.border(
                width = 1.dp,
                color = if (isSelected) Azure else DustyGray.copy(alpha = 0.5f),
                shape = RoundedCornerShape(20.dp)
            ).clip(RoundedCornerShape(20.dp)).clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp), contentAlignment = Alignment.Center
    ) {
        Text(
            text = year,
            color = if (isSelected) Azure else DustyGray,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            fontSize = 14.sp
        )
    }
}

@Composable
fun MonthItem(
    index: Int,
    monthStatus: MonthStatus,
    onDownloadClick: () -> Unit,
) {

    val backgroundColor = if (index % 2 == 0) AliceBlue_25 else BackgroundLight

    Row(
        modifier = Modifier.fillMaxWidth().background(backgroundColor)
            .border(width = 0.3.dp, color = Alto_5)// Added background
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = monthStatus.month,
            style = TextStyles.InterRegularXS,
        )

        Row(
            modifier = Modifier.clickable(enabled = monthStatus.isAvailable) { onDownloadClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_download_svg),
                contentDescription = "Download",
                modifier = Modifier.size(16.dp),
                tint = if (monthStatus.isAvailable) Congress_blue else DustyGray.copy(alpha = 0.5f)
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = "Download",
                style = TextStyles.InterSemiBoldXS,
                color = if (monthStatus.isAvailable) Congress_blue else DustyGray.copy(alpha = 0.5f)
            )
        }
    }
}