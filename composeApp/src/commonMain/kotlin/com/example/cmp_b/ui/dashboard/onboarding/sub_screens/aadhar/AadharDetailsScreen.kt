package com.example.cmp_b.ui.dashboard.onboarding.sub_screens.aadhar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cmp_b.composeapp.generated.resources.Res
import cmp_b.composeapp.generated.resources.ic_arrow_back
import cmp_b.composeapp.generated.resources.png_top_header
import com.example.cmp_b.navigation.AppState
import com.example.cmp_b.navigation.NavigationEvent
import com.example.cmp_b.ui.bottomsheet.ImageSourceBottomSheet
import com.example.cmp_b.ui.components.AadharVisualTransformation
import com.example.cmp_b.ui.components.AppCustomButton
import com.example.cmp_b.ui.components.AppCustomDialog
import com.example.cmp_b.ui.components.AppCustomFileViewer
import com.example.cmp_b.ui.components.AppCustomRadioGroup
import com.example.cmp_b.ui.components.AppDatePickerWithLabel
import com.example.cmp_b.ui.components.AppDropdownWithLabel
import com.example.cmp_b.ui.components.AppTextFieldWithLabel
import com.example.cmp_b.ui.components.AppUploadAadharField
import com.example.cmp_b.ui.theme.AppGradients
import com.example.cmp_b.ui.theme.BackgroundLight
import com.example.cmp_b.ui.theme.Primary
import com.example.cmp_b.ui.theme.TextStyles
import com.example.cmp_b.util.FilePickerUtils
import com.example.cmp_b.util.PermissionUtils
import org.jetbrains.compose.resources.painterResource

@Composable
fun AadharDetailsScreen(
    appState: AppState,
    viewModel: AadharDetailsViewModel,
) {

    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    var showBottomSheet by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    var selectedImageUri by remember { mutableStateOf<String?>(null) }

    var cameraTempUri by remember { mutableStateOf<String?>(null) }

    var showViewer by remember { mutableStateOf(false) }

    // --------- Gallery Launcher ---------

    val galleryLauncher = FilePickerUtils.rememberGalleryLauncher { uri ->
        uri?.let {
            selectedImageUri = it

            viewModel.onEvent(
                AadharDetailsEvent.OnFileSelected(it)
            )
        }
    }

    // --------- Document Launcher ---------

    val documentLauncher = FilePickerUtils.rememberDocumentLauncher { uri ->
        uri?.let {

            selectedImageUri = it

            viewModel.onEvent(
                AadharDetailsEvent.OnFileSelected(it)
            )
        }
    }

    // --------- Camera Launcher ---------

    val cameraLauncher = FilePickerUtils.rememberCameraLauncher {

        cameraTempUri?.let {

            selectedImageUri = it

            viewModel.onEvent(
                AadharDetailsEvent.OnFileSelected(it)
            )
        }
    }

    // -------- Camera Permission ---------

    val cameraPermissionLauncher =
        PermissionUtils.rememberCameraPermissionLauncher { granted ->

            if (granted) {

                cameraTempUri = FilePickerUtils.createTempImageUri()
                cameraLauncher.launch(cameraTempUri!!)

            } else {
                // Simplified for commonMain, typically handled in actual implementation
                appState.showSnackBar("Camera permission denied")
            }
        }

    // -------- Navigation Observer ---------
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { message ->
            appState.showSnackBar(message)
        }
    }

    LaunchedEffect(Unit) {

        viewModel.navigation.collect { event ->

            when (event) {

                is NavigationEvent.PopBack -> {
                    appState.navigator.popBack()
                }

                is NavigationEvent.Navigate -> {
                    appState.navigator.navigate(event.route)
                }

                else -> {}
            }
        }
    }

    // -------- Loading State ---------

    if (uiState.isLoading) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

        return
    }

    // =========================================================
    // MAIN UI
    // =========================================================

    Box(
        modifier = Modifier.fillMaxSize()
            .background(AppGradients.lightPrimaryBackground())

    ) {

        // -----------------------------------------------------
        // SCROLLABLE CONTENT
        // -----------------------------------------------------

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {

            Spacer(modifier = Modifier.height(220.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                // ---------------- Name ----------------

                AppTextFieldWithLabel(

                    labelText = "Name As Per Aadhaar Card",

                    value = uiState.name,

                    hint = "Enter Name",

                    onValueChange = {

                        viewModel.onEvent(
                            AadharDetailsEvent.OnNameChanged(it)
                        )
                    },
                    keyboardType = KeyboardType.Email
                )

                Spacer(modifier = Modifier.height(20.dp))

                // ---------------- Aadhaar ----------------

                AppTextFieldWithLabel(

                    labelText = "Aadhaar Card Number",

                    value = uiState.aadharNumber,

                    hint = "Enter Number",

                    keyboardType = KeyboardType.Number,

                    visualTransformation = AadharVisualTransformation(),

                    onValueChange = {

                        viewModel.onEvent(
                            AadharDetailsEvent.OnAadharChanged(it)
                        )
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                // ---------------- Gender ----------------
                AppCustomRadioGroup(

                    labelText = "Gender",

                    options = viewModel.options,

                    selectedId = uiState.gender,

                    onOptionSelected = { id ->

                        viewModel.onEvent(
                            AadharDetailsEvent.OnGenderChanged(id.toString())
                        )
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                // ---------------- DOB ----------------

                AppDatePickerWithLabel(

                    labelText = "Date Of Birth",

                    hint = "Select Date",

                    selectedDate = uiState.dob,

                    isTodayMax = true,

                    onDateSelected = {
                        viewModel.onEvent(
                            AadharDetailsEvent.OnDobSelected(it)
                        )
                    },
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.height(20.dp))

                // ---------------- Mobile ----------------

                AppTextFieldWithLabel(

                    labelText = "Mobile Number",

                    value = uiState.mobileNumber,

                    hint = "Enter Mobile Number",

                    keyboardType = KeyboardType.Number,

                    onValueChange = {

                        viewModel.onEvent(
                            AadharDetailsEvent.OnMobileChanged(it)
                        )
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))


                AppTextFieldWithLabel(

                    labelText = "Father/Husband Name",

                    value = uiState.fatherName,

                    hint = "Enter Father/Husband Name",

                    onValueChange = {

                        viewModel.onEvent(
                            AadharDetailsEvent.OnFatherNameChanged(it)
                        )
                    },

                    )

                Spacer(modifier = Modifier.height(20.dp))

                // ---------------- Dropdown ----------------

                AppDropdownWithLabel(

                    labelText = "Dropdown Format",

                    hint = "Choose Item",

                    itemList = listOf("Option 1", "Option 2", "Option 3"),

                    selectedItem = uiState.selectedDropdown,

                    onSelectedItem = {

                        viewModel.onEvent(
                            AadharDetailsEvent.OnDropdownSelected(it)
                        )
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                // ---------------- Upload Aadhaar ----------------

                AppUploadAadharField(

                    labelText = "Upload Aadhaar Card",

                    fileUri = uiState.fileUri,

                    fileName = uiState.fileName,

                    fileSize = uiState.fileSize,
                    onUploadClick = {

                        showBottomSheet = true
                    },

                    onViewClick = {
                        showViewer = true
                    },

                    onRemoveClick = {
                        viewModel.onEvent(
                            AadharDetailsEvent.OnFileSelected(uri = "")
                        )
                    }
                )

                Spacer(modifier = Modifier.height(80.dp))
            }
        }

        // -----------------------------------------------------
        // HEADER
        // -----------------------------------------------------

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .align(Alignment.TopCenter)
        ) {

            Image(
                painter = painterResource(Res.drawable.png_top_header),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.FillBounds
            )

            Box(
                modifier = Modifier
                    .statusBarsPadding()
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {

                IconButton(
                    onClick = {
                        viewModel.onEvent(AadharDetailsEvent.OnBackClick)
                    },
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_arrow_back),
                        contentDescription = null,
                        tint = Color.White
                    )
                }

                Text(
                    text = "Aadhar Details",
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center,
                    style = TextStyles.InterSemiBoldM,
                    color = BackgroundLight
                )
            }
        }

        // -----------------------------------------------------
        // SUBMIT BUTTON
        // -----------------------------------------------------

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

                firstButtonText = "Submit",

                bgColor = Primary,

                onFirstButtonClick = {

                    viewModel.onEvent(
                        AadharDetailsEvent.OnSubmitClick
                    )
                }
            )
        }


    }

    if (showViewer) {
        AppCustomFileViewer(
            headText = "View Aadhaar Card",
            fileUri = selectedImageUri,
            onDismiss = { showViewer = false }
        )
    }
    // =========================================================
    // PERMISSION DIALOG
    // =========================================================

    if (showDialog) {

        AppCustomDialog(

            showDialog = true,

            title = "Camera Permission Needed",

            message = "Please enable camera permission from settings.",

            confirmText = "Open Settings",

            dismissText = "Cancel",

            onConfirm = {
                // Settings opening should be handled by platform specific code
                showDialog = false
            },

            onDismiss = {
                showDialog = false
            }
        )
    }

    // =========================================================
    // IMAGE SOURCE BOTTOM SHEET
    // =========================================================

    if (showBottomSheet) {

        ImageSourceBottomSheet(

            onCameraSelected = {

                showBottomSheet = false

                if (PermissionUtils.hasCameraPermission()) {

                    cameraTempUri =
                        FilePickerUtils.createTempImageUri()

                    cameraLauncher.launch(cameraTempUri!!)

                } else {

                    cameraPermissionLauncher.launch(
                        "android.permission.CAMERA"
                    )
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

            onDismiss = {

                showBottomSheet = false
            }
        )
    }
}
