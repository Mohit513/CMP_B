package com.example.cmp_b.ui.dashboard.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cmp_b.composeapp.generated.resources.Res
import cmp_b.composeapp.generated.resources.ic_arrow_back
import cmp_b.composeapp.generated.resources.ic_edit_white
import cmp_b.composeapp.generated.resources.ic_placeholder_profile
import cmp_b.composeapp.generated.resources.image_top_login
import com.example.cmp_b.navigation.AppState
import com.example.cmp_b.navigation.NavigationEvent
import com.example.cmp_b.ui.bottomsheet.ImageSourceBottomSheet
import com.example.cmp_b.ui.components.*
import com.example.cmp_b.ui.dashboard.profile.vm.EditProfileEvent
import com.example.cmp_b.ui.dashboard.profile.vm.ProfileViewModel
import com.example.cmp_b.ui.theme.AppGradients
import com.example.cmp_b.ui.theme.BackgroundLight
import com.example.cmp_b.ui.theme.Primary
import com.example.cmp_b.util.FilePickerUtils
import com.example.cmp_b.util.PermissionUtils
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.painterResource

@Composable
fun EditProfileScreen(
    appState: AppState,
    profileViewModel: ProfileViewModel
) {
    val scrollState = rememberScrollState()
    val uiState by profileViewModel.editUiState.collectAsState()
    
    var cameraTempUri by remember { mutableStateOf<String?>(null) }
    var showBottomSheet by remember { mutableStateOf(false) }

    val galleryLauncher = FilePickerUtils.rememberGalleryLauncher { uri ->
        uri?.let { profileViewModel.onEditEvent(EditProfileEvent.OnProfileImageChanged(it)) }
    }

    val documentLauncher = FilePickerUtils.rememberDocumentLauncher { uri ->
        uri?.let { profileViewModel.onEditEvent(EditProfileEvent.OnProfileImageChanged(it)) }
    }

    val cameraLauncher = FilePickerUtils.rememberCameraLauncher { success ->
        if (success) {
            cameraTempUri?.let { profileViewModel.onEditEvent(EditProfileEvent.OnProfileImageChanged(it)) }
        }
    }

    val cameraPermissionLauncher = PermissionUtils.rememberCameraPermissionLauncher { granted ->
        if (granted) {
            val uri = FilePickerUtils.createTempImageUri()
            cameraTempUri = uri
            cameraLauncher.launch(uri)
        } else {
            appState.showSnackBar("Camera permission denied")
        }
    }

    LaunchedEffect(Unit) {
        profileViewModel.uiEvent.collect { message ->
            appState.showSnackBar(message)
        }
    }

    LaunchedEffect(Unit) {
        profileViewModel.navigation.collect { event ->
            when (event) {
                is NavigationEvent.PopBack -> appState.navigator.popBack()
                else -> {}
            }
        }
    }

    if (uiState.isLoading) {
        Box(modifier = Modifier.fillMaxSize().background(AppGradients.lightPrimaryBackground()), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    Box(modifier = Modifier.fillMaxSize()
        .background(AppGradients.lightPrimaryBackground())) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(230.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                AppCommonFillHeaderView(headText = "Personal Details")

                Spacer(modifier = Modifier.height(24.dp))

                AppTextFieldWithLabel(
                    labelText = "Mobile Number",
                    hint = "Enter Mobile Number",
                    value = uiState.mobileNumber,
                    keyboardType = KeyboardType.Number,
                    onValueChange = { profileViewModel.onEditEvent(EditProfileEvent.OnMobileChanged(it)) }
                )

                Spacer(modifier = Modifier.height(24.dp))

                AppTextFieldWithLabel(
                    labelText = "Email",
                    hint = "Enter Email",
                    value = uiState.email,
                    keyboardType = KeyboardType.Email,
                    onValueChange = { profileViewModel.onEditEvent(EditProfileEvent.OnEmailChanged(it)) }
                )

                Spacer(modifier = Modifier.height(24.dp))

                AppTextFieldWithLabel(
                    labelText = "Aadhar Number",
                    hint = "Enter Aadhar Number",
                    value = uiState.aadharNumber,
                    keyboardType = KeyboardType.Number,
                    visualTransformation = AadharVisualTransformation(),
                    onValueChange = { profileViewModel.onEditEvent(EditProfileEvent.OnAadharChanged(it)) }
                )

                Spacer(modifier = Modifier.height(24.dp))

                AppDatePickerWithLabel(
                    labelText = "Date Of Birth",
                    selectedDate = uiState.dob,
                    hint = "Select Date",
                    isTodayMax = true,
                    onDateSelected = { profileViewModel.onEditEvent(EditProfileEvent.OnDobSelected(it)) }
                )

                Spacer(modifier = Modifier.height(24.dp))

                AppCommonChipSelector(
                    labelText = "Marital Status",
                    options = listOf("Married", "Unmarried", "Divorced", "Widow"),
                    selectedOption = uiState.maritalStatus,
                    onOptionSelected = { profileViewModel.onEditEvent(EditProfileEvent.OnMaritalStatusChanged(it)) }
                )

                Spacer(modifier = Modifier.height(24.dp))

                AppDropdownWithLabel(
                    labelText = "Blood Group",
                    hint = "Select Blood Group",
                    itemList = listOf("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"),
                    selectedItem = uiState.bloodGroup,
                    onSelectedItem = { profileViewModel.onEditEvent(EditProfileEvent.OnBloodGroupChanged(it)) }
                )

                Spacer(modifier = Modifier.height(32.dp))

                AppCommonFillHeaderView(headText = "Employee Details")

                Spacer(modifier = Modifier.height(24.dp))

                AppTextFieldWithLabel(
                    labelText = "Client Name",
                    hint = "Enter Client Name",
                    value = uiState.clientName,
                    onValueChange = { profileViewModel.onEditEvent(EditProfileEvent.OnClientNameChanged(it)) }
                )

                Spacer(modifier = Modifier.height(24.dp))

                AppTextFieldWithLabel(
                    labelText = "Reporting Manager",
                    hint = "Enter Reporting Manager",
                    value = uiState.reportingManager,
                    onValueChange = { profileViewModel.onEditEvent(EditProfileEvent.OnReportingManagerChanged(it)) }
                )

                Spacer(modifier = Modifier.height(24.dp))

                AppTextFieldWithLabel(
                    labelText = "Innov Id",
                    hint = "Enter Innov Id",
                    value = uiState.innovId,
                    keyboardType = KeyboardType.Number,
                    onValueChange = { profileViewModel.onEditEvent(EditProfileEvent.OnInnovIdChanged(it)) }
                )

                Spacer(modifier = Modifier.height(24.dp))

                AppTextFieldWithLabel(
                    labelText = "Employee Id",
                    hint = "Enter Employee Id",
                    value = uiState.employeeId,
                    keyboardType = KeyboardType.Number,
                    onValueChange = { profileViewModel.onEditEvent(EditProfileEvent.OnEmployeeIdChanged(it)) }
                )

                Spacer(modifier = Modifier.height(24.dp))

                AppTextFieldWithLabel(
                    labelText = "UAN Number",
                    hint = "Enter UAN Number",
                    value = uiState.uanNumber,
                    keyboardType = KeyboardType.Number,
                    onValueChange = { profileViewModel.onEditEvent(EditProfileEvent.OnUanChanged(it)) }
                )

                Spacer(modifier = Modifier.height(24.dp))

                AppTextFieldWithLabel(
                    labelText = "RF Number",
                    hint = "Enter RF Number",
                    value = uiState.rfNumber,
                    keyboardType = KeyboardType.Number,
                    onValueChange = { profileViewModel.onEditEvent(EditProfileEvent.OnRfChanged(it)) }
                )

                Spacer(modifier = Modifier.height(32.dp))

                AppCommonFillHeaderView(headText = "Address Details")

                Spacer(modifier = Modifier.height(24.dp))

                AppDropdownWithLabel(
                    labelText = "City",
                    hint = "Select City",
                    itemList = listOf("Itarsi", "Bhopal", "Mumbai", "Agra"),
                    selectedItem = uiState.city,
                    onSelectedItem = { profileViewModel.onEditEvent(EditProfileEvent.OnCityChanged(it)) }
                )

                Spacer(modifier = Modifier.height(24.dp))

                AppDropdownWithLabel(
                    labelText = "State",
                    hint = "Select State",
                    itemList = listOf("Madhya Pradesh", "Maharashtra", "Uttar Pradesh", "Jammu & Kashmir"),
                    selectedItem = uiState.state,
                    onSelectedItem = { profileViewModel.onEditEvent(EditProfileEvent.OnStateChanged(it)) }
                )

                Spacer(modifier = Modifier.height(24.dp))

                AppLabelWithContainer(
                    labelText = "Address",
                    description = "Enter Address",
                    value = uiState.address,
                    onValueChange = { profileViewModel.onEditEvent(EditProfileEvent.OnAddressChanged(it)) }
                )

                Spacer(modifier = Modifier.height(120.dp))
            }
        }

        // ---------------- HEADER ----------------
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .align(Alignment.TopCenter)
        ) {
            Image(
                painter = painterResource(Res.drawable.image_top_login),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp),
                contentScale = ContentScale.FillBounds
            )

            Row(
                modifier = Modifier
                    .statusBarsPadding()
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                IconButton(onClick = { profileViewModel.onEditEvent(EditProfileEvent.OnBackClick) }) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_arrow_back),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }

            Box(
                modifier = Modifier
                    .size(130.dp)
                    .align(Alignment.BottomCenter)
            ) {

                if (uiState.profileImage.isNotEmpty()) {

                    AsyncImage(
                        model = uiState.profileImage,
                        contentDescription = null,
                        modifier = Modifier
                            .matchParentSize()
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                } else {

                    Image(
                        painter = painterResource(Res.drawable.ic_placeholder_profile),
                        contentDescription = null,
                        modifier = Modifier
                            .matchParentSize()
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }

                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.BottomEnd)
                        .border(2.dp, BackgroundLight, CircleShape)
                        .clip(CircleShape)
                        .background(Primary)
                        .clickable { showBottomSheet = true },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_edit_white),
                        contentDescription = "Edit",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(15.dp)
                    )
                }
            }
        }

        // ---------------- SAVE BUTTON ----------------
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(BackgroundLight)
        ) {
            AppCustomButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                firstButtonText = "Save",
                bgColor = Primary,
                onFirstButtonClick = { profileViewModel.onEditEvent(EditProfileEvent.OnSaveClick) }
            )
        }
    }

    if (showBottomSheet) {
        ImageSourceBottomSheet(
            onCameraSelected = {
                showBottomSheet = false
                if (PermissionUtils.hasCameraPermission()) {
                    cameraTempUri = FilePickerUtils.createTempImageUri()

                    cameraTempUri?.let {
                        cameraLauncher.launch(it)
                    }
                } else {
                    cameraPermissionLauncher.launch("android.permission.CAMERA")
                }
            },
            onGallerySelected = {
                showBottomSheet = false
                galleryLauncher.launch("image/*")
            },
            onFileSelected = {
                showBottomSheet = false
                documentLauncher.launch(arrayOf("*/*"))
            },
            onDismiss = { showBottomSheet = false }
        )
    }
}
