package com.example.cmp_b.ui.dashboard.letter.feature.offer_letter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.cmp_b.navigation.AppState
import com.example.cmp_b.ui.components.AppSimpleTopBar
import com.example.cmp_b.ui.theme.AppGradients
import com.example.cmp_b.ui.theme.BackgroundLight
import com.example.cmp_b.ui.theme.BrightRed
import com.example.cmp_b.ui.theme.Cerenade
import com.example.cmp_b.ui.theme.Congress_blue
import com.example.cmp_b.ui.theme.FunGreen_50
import com.example.cmp_b.ui.theme.HintOfGreen
import com.example.cmp_b.ui.theme.IndoShine
import com.example.cmp_b.ui.theme.JapaneseLauren
import com.example.cmp_b.ui.theme.Mercury
import com.example.cmp_b.ui.theme.Pippin_15
import com.example.cmp_b.ui.theme.RedBerry
import com.example.cmp_b.ui.theme.Silver
import com.example.cmp_b.ui.theme.Solitude
import com.example.cmp_b.ui.theme.TextStyles

import com.example.cmp_b.ui.dashboard.letter.feature.offer_letter.vm.OfferLetterUiState
import com.example.cmp_b.ui.dashboard.letter.feature.offer_letter.vm.OfferLetterViewModel
import com.example.cmp_b.ui.dashboard.letter.feature.offer_letter.vm.OfferStatus
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun OfferLetterScreen(
    appState: AppState,
    viewModel: OfferLetterViewModel = koinViewModel(),
) {
    val offers by viewModel.offers.collectAsState()
    val showSignature = viewModel.showSignatureSheet
    val showReason = viewModel.showReasonSheet
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppGradients.lightPrimaryBackground())
    ) {
        Scaffold(
            topBar = {
                AppSimpleTopBar(
                    title = "Offer Letter",
                    onBackClick = { appState.navigator.popBack() },
                    showBackButton = true
                )
            }
        )
        { padding ->

            LazyColumn(
                contentPadding = padding,
                modifier = Modifier.fillMaxSize()
            ) {
                items(offers) { offer ->

                    OfferLetterCard(
                        offer = offer,
                        onDownloadClick = {
                            // TODO: Implement platform-specific download
                        },
                        onAccept = { viewModel.onAcceptClick(offer.id) },
                        onReject = { viewModel.onRejectClick(offer.id) }
                    )
                }
            }
            if (showSignature) {
                SignatureBottomSheet(
                    show = viewModel.showSignatureSheet,
                    onDismiss = { viewModel.showSignatureSheet = false },
                    onSubmit = { viewModel.submitSignature() }
                )
            }

            if (showReason) {
                ReasonBottomSheet(
                    show = viewModel.showReasonSheet,
                    onDismiss = { viewModel.showReasonSheet = false },
                    onSubmit = { viewModel.submitReason() }
                )
            }
        }
    }


}

@Composable
fun OfferLetterCard(
    offer: OfferLetterUiState,
    onDownloadClick: () -> Unit,
    onAccept: () -> Unit,
    onReject: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(0.7.dp, Mercury)
    ) {

        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = offer.companyName,
                    style = TextStyles.InterSemiBoldM
                )
                StatusChip(status = offer.status)
            }
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 16.dp),
                thickness = 0.7.dp,
                color = Silver
            )
        }


        Row(modifier = Modifier.background(BackgroundLight)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .background(BackgroundLight)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Role",
                            style = TextStyles.InterRegularXS,
                            color = Color.Gray
                        )
                        Text(
                            text = offer.role,
                            style = TextStyles.InterRegularS
                        )
                    }

                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = "Joining Date",
                            style = TextStyles.InterRegularXS,
                            color = Color.Gray
                        )
                        Text(
                            text = offer.joiningDate,
                            style = TextStyles.InterRegularS,
                            textAlign = TextAlign.End
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                        .clickable { onDownloadClick() }
                        .background(Solitude)
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Icon placeholder for multiplatform compatibility
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Offer Letter.pdf",
                        color = Congress_blue,
                        style = TextStyles.InterRegularS,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    // Download icon placeholder for multiplatform compatibility
                }


                if (offer.status == OfferStatus.AWAITING) {

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                            .padding(bottom = 12.dp)
                    ) {

                        Button(
                            onClick = onReject,
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = BrightRed),
                            modifier = Modifier
                                .weight(1f)
                                .height(40.dp)
                        ) {
                            Text("Reject", color = BackgroundLight)
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Button(
                            onClick = onAccept,
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = FunGreen_50),
                            modifier = Modifier
                                .weight(1f)
                                .height(40.dp)
                        ) {
                            Text("Accept", color = BackgroundLight)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StatusChip(status: OfferStatus) {

    val (text, bgColor, textColor) = when (status) {
        OfferStatus.AWAITING -> Triple(
            "Awaiting",
            Cerenade,        // light orange bg
            IndoShine       // dark orange text
        )

        OfferStatus.ACCEPTED -> Triple(
            "Accepted",
            HintOfGreen,     // light green bg
            JapaneseLauren      // dark green text
        )

        OfferStatus.REJECTED -> Triple(
            "Rejected",
            Pippin_15,       // light red bg
            RedBerry        // dark red text
        )
    }

    Box(
        modifier = Modifier.background(bgColor, RoundedCornerShape(16.dp))
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            style = TextStyles.InterRegularXS
        )
    }
}

@Composable
fun SignatureBottomSheet(
    show: Boolean,
    onDismiss: () -> Unit,
    onSubmit: () -> Unit,
) {
    // TODO: Implement platform-specific signature pad
    // For now, just show a simple confirmation dialog
    AppBottomSheet(show = show, onDismiss = onDismiss) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "Add your signature",
                style = TextStyles.InterSemiBoldM
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Signature pad not available in common code. Implement platform-specific version.",
                style = TextStyles.InterRegularS
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onSubmit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Congress_blue),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Submit", color = BackgroundLight)
            }
        }
    }
}

@Composable
fun ReasonBottomSheet(
    show: Boolean,
    onDismiss: () -> Unit,
    onSubmit: (String) -> Unit,
) {
    var reason by remember { mutableStateOf("") }

    AppBottomSheet(show = show, onDismiss = onDismiss) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "Reason for rejection",
                style = TextStyles.InterSemiBoldM
            )
            Spacer(modifier = Modifier.height(16.dp))
            // TODO: Replace with proper text field component
            Text(
                "Enter reason here",
                style = TextStyles.InterRegularS,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Solitude, RoundedCornerShape(8.dp))
                    .padding(12.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { onSubmit(reason) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Congress_blue),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Submit", color = BackgroundLight)
            }
        }
    }
}

@Composable
fun AppBottomSheet(
    show: Boolean,
    onDismiss: () -> Unit,
    content: @Composable () -> Unit,
) {
    if (!show) return

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.4f))
            .clickable { onDismiss() }
    ) {

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    BackgroundLight,
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                )
                .clickable(enabled = false) {}
        ) {
            content()
        }
    }
}
