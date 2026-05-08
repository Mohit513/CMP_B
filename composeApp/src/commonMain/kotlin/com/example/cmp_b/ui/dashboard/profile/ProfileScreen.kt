package com.example.cmp_b.ui.dashboard.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cmp_b.composeapp.generated.resources.Res
import cmp_b.composeapp.generated.resources.ic_arrow_back
import cmp_b.composeapp.generated.resources.ic_bin_fill_svg
import cmp_b.composeapp.generated.resources.ic_delet_svg
import cmp_b.composeapp.generated.resources.ic_edit_svg
import cmp_b.composeapp.generated.resources.ic_placeholder_profile
import cmp_b.composeapp.generated.resources.image_top_login
import com.example.cmp_b.navigation.AppState
import com.example.cmp_b.navigation.NavigationEvent
import com.example.cmp_b.ui.components.AppCustomButton
import com.example.cmp_b.ui.components.AppCustomCardWithLabel
import com.example.cmp_b.ui.components.AppCustomDialog
import com.example.cmp_b.ui.components.AppKeyValue
import com.example.cmp_b.ui.dashboard.profile.vm.ProfileEvent
import com.example.cmp_b.ui.dashboard.profile.vm.ProfileViewModel
import com.example.cmp_b.ui.theme.AppGradients
import com.example.cmp_b.ui.theme.Bright_red
import com.example.cmp_b.ui.theme.Primary
import com.example.cmp_b.ui.theme.TextStyles
import com.example.cmp_b.ui.theme.Your_pink
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    appState: AppState,
    profileViewModel: ProfileViewModel,
) {
    val uiState by profileViewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        profileViewModel.navigation.collect { event ->
            when (event) {
                is NavigationEvent.Navigate -> appState.navigator.navigate(event.route)
                is NavigationEvent.PopBack -> appState.navigator.popBack()
                is NavigationEvent.PopBackWithResult -> appState.navigator.popBackWithResult(
                    event.key,
                    event.value
                )

                is NavigationEvent.ClearBackStackAndNavigate -> appState.navigator.clearAndNavigate(
                    event.route
                )

                else -> {}
            }
        }
    }

    if (uiState.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    Box(
        modifier = Modifier.fillMaxSize().background(AppGradients.lightPrimaryBackground())
    ) {

        // ---------------- SCROLLABLE CONTENT ----------------

        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(240.dp))

            Text(
                text = uiState.userName,
                style = TextStyles.InterSemiBoldXL,
                modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            AppCustomCardWithLabel(
                cardLabel = "Personal Details",
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            ) {

                FlowRow(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    maxItemsInEachRow = 2
                ) {

                    val itemModifier = Modifier.fillMaxWidth(0.45f)

                    uiState.personalDetails.forEach {
                        AppKeyValue(
                            modifier = itemModifier, key = it.key, value = it.value
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            AppCustomCardWithLabel(
                cardLabel = "Employment Details",
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            ) {

                FlowRow(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    maxItemsInEachRow = 2
                ) {

                    val itemModifier = Modifier.fillMaxWidth(0.45f)

                    uiState.employmentDetails.forEach {
                        AppKeyValue(
                            modifier = itemModifier, key = it.key, value = it.value
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            AppCustomCardWithLabel(
                cardLabel = "Address Details",
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            ) {

                FlowRow(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    maxItemsInEachRow = 2
                ) {

                    val itemModifier = Modifier.fillMaxWidth(0.45f)

                    uiState.addressDetails.forEach {
                        AppKeyValue(
                            modifier = itemModifier, key = it.key, value = it.value
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            AppCustomButton(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                firstButtonText = "Logout",
                contentColor = Bright_red,
                bgColor = Your_pink,
                onFirstButtonClick = {
                    showDialog = true
                })

            Spacer(modifier = Modifier.height(40.dp))
        }


        Box(
            modifier = Modifier.fillMaxWidth().height(210.dp).align(Alignment.TopCenter)
        ) {

            Image(
                painter = painterResource(Res.drawable.image_top_login),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.80f),
                contentScale = ContentScale.FillBounds
            )

            Row(
                modifier = Modifier.statusBarsPadding().fillMaxWidth()
                    .padding(horizontal = 4.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(
                    onClick = { profileViewModel.onEvent(ProfileEvent.OnBackClick) }) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_arrow_back),
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                Row(modifier = Modifier.padding(end = 12.dp)) {

                    HeaderActionButton(
                        icon = Res.drawable.ic_edit_svg, onClick = {
                            profileViewModel.onEvent(ProfileEvent.OnEditClick)
                        })

                    Spacer(modifier = Modifier.width(10.dp))

                    HeaderActionButton(
                        icon = Res.drawable.ic_delet_svg, onClick = {
                            profileViewModel.onEvent(ProfileEvent.OnDeleteClick)
                        })
                }
            }

            Box(
                modifier = Modifier.align(Alignment.BottomCenter).padding(top = 30.dp)
            ) {
                if (uiState.profileImage.isNotEmpty()) {
                    AsyncImage(
                        model = uiState.profileImage,
                        contentDescription = "Profile Picture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(130.dp).clip(CircleShape)
                    )
                } else {
                    Image(
                        painter = painterResource(Res.drawable.ic_placeholder_profile),
                        contentDescription = "Profile Picture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(130.dp).clip(CircleShape)
                    )
                }
            }
        }

        // ---------------- TRANSITION LOADER ----------------

        if (uiState.isEditLoading) {
            Box(
                modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.3f))
                    .clickable(enabled = false) {}, contentAlignment = Alignment.Center
            ) {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier.size(100.dp),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = Primary)
                    }
                }
            }
        }
    }
    if (showDialog) {

        AppCustomDialog(
            showDialog = true, title = "Logout", message = "Are you sure, want to logout ?",

            confirmText = "Yes", dismissText = "No",

            onConfirm = {
                profileViewModel.onEvent(ProfileEvent.OnLogoutClick)
                showDialog = false
            },

            onDismiss = {
                showDialog = false
            })
    }
}

@Composable
fun HeaderActionButton(
    icon: DrawableResource,
    onClick: () -> Unit = {},
) {

    Surface(
        modifier = Modifier.size(width = 35.dp, height = 28.dp),
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 4.dp
    ) {
        IconButton(
            onClick = onClick, modifier = Modifier.fillMaxSize()

        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}