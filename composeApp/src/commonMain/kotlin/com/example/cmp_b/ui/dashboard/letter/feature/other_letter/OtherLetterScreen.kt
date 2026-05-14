package com.example.digi_trac_v5.ui.presentation.screens.dashboard.features.other_letter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digi_trac_v5.R
import com.example.digi_trac_v5.ui.components.components.AppSimpleTopBar
import com.example.digi_trac_v5.ui.presentation.screens.dashboard.features.other_letter.vm.OtherLetterStatus
import com.example.digi_trac_v5.ui.presentation.screens.dashboard.features.other_letter.vm.OtherLetterUiState
import com.example.digi_trac_v5.ui.presentation.screens.dashboard.features.other_letter.vm.OtherLetterViewModel
import com.example.digi_trac_v5.ui.navigation.nav.AppState
import com.example.digi_trac_v5.ui.theme.Congress_blue
import com.example.digi_trac_v5.ui.theme.FunGreen_50
import com.example.digi_trac_v5.ui.theme.Silver
import com.example.digi_trac_v5.ui.theme.Solitude
import com.example.digi_trac_v5.ui.theme.TextStyles
import com.example.digi_trac_v5.util.AppUtils

@Composable
fun OtherLetterScreen(
    appState: AppState,
    viewModel: OtherLetterViewModel = hiltViewModel(),
) {

    val letters = viewModel.letters.collectAsState().value
    val context = LocalContext.current

    Scaffold(
        topBar = {
            AppSimpleTopBar(
                title = "Other Letter",
                onBackClick = { appState.navigator.popBack() },
                showBackButton = true
            )
        },
    ) { padding ->

        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize()
        ) {

            items(letters, key = { it.id }) { letter ->

                OtherLetterCard(
                    letter = letter,

                    onDownloadClick = {
                        AppUtils.downloadFile(context = context, fileName = letter.fileName,
                            url = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf" )
                    },

                    onAccept = {
                        viewModel.onAcceptClick(letter.id)
                    }
                )
            }
        }
    }
}

@Composable
fun OtherLetterCard(
    letter: OtherLetterUiState,
    onDownloadClick: () -> Unit,
    onAccept: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {

        Column {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .padding(top = 18.dp)
                        .height(24.dp)
                        .background(
                            color = Congress_blue,
                            shape = RoundedCornerShape(
                                topEnd = 16.dp,
                                bottomEnd = 16.dp
                            )
                        )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = letter.title,
                        style = TextStyles.InterSemiBoldM,
                        modifier = Modifier.weight(1f)
                    )

                    if (letter.status == OtherLetterStatus.PENDING) {
                        Button(
                            onClick = onAccept,
                            contentPadding = PaddingValues(horizontal = 12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Congress_blue),
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier.height(30.dp)
                        ) {
                            Text(
                                "Accept",
                                style = TextStyles.InterMediumXS,
                                color = Color.White
                            )
                        }
                    } else {
                        Button(
                            onClick = onAccept,
                            contentPadding = PaddingValues(horizontal = 8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = FunGreen_50),
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier.height(30.dp)
                        )
                        {
                            Text(
                                "Accepted",
                                color = Color.White,
                                style = TextStyles.InterMediumXS
                            )
                        }
                    }
                }
            }

            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 16.dp),
                color = Silver
            )

            Column(modifier = Modifier.padding(16.dp)) {

                Text("Remark", style = TextStyles.InterSemiBoldS, color = Color.Black)
                Text(letter.remark, style = TextStyles.InterRegularS, color = Color.Gray)

                Spacer(modifier = Modifier.height(12.dp))

                Text("Generated On", style = TextStyles.InterSemiBoldS, color = Color.Black)
                Text(letter.generatedOn, style = TextStyles.InterRegularS, color = Color.Gray)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Solitude)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.png_myletters),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(16.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = letter.fileName,
                        style = TextStyles.InterRegularS,
                        color = Color.Gray
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        onDownloadClick()
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_download_svg),
                        contentDescription = null,
                        tint = Congress_blue,
                        modifier = Modifier.size(16.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        "Download",
                        style = TextStyles.InterMediumXS,
                        color = Congress_blue
                    )
                }
            }
        }
    }
}