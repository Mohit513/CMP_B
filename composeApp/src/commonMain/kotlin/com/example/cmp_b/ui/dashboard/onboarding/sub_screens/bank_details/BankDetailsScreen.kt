package com.example.cmp_b.ui.dashboard.onboarding.sub_screens.bank_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.dp
import cmp_b.composeapp.generated.resources.Res
import cmp_b.composeapp.generated.resources.ic_arrow_back
import cmp_b.composeapp.generated.resources.png_top_header
import com.example.cmp_b.navigation.AppState
import com.example.cmp_b.navigation.NavigationEvent
import com.example.cmp_b.ui.bottomsheet.ImageSourceBottomSheet
import com.example.cmp_b.ui.components.AppCustomButton
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
fun BankDetailsScreen(
    appState: AppState,
    viewModel: BankDetailsViewModel
) {

    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    var cameraTempUri by remember { mutableStateOf<String?>(null) }
    var showBottomSheet by remember { mutableStateOf(false) }
    var showViewer by remember { mutableStateOf(false) }


    // -------- Gallery Launcher --------
    val galleryLauncher = FilePickerUtils.rememberGalleryLauncher { uri ->
        uri?.let {
            viewModel.onEvent(BankDetailsEvent.OnFileSelected(it))
        }
    }

    // -------- Document Launcher --------
    val documentLauncher = FilePickerUtils.rememberDocumentLauncher { uri ->
        uri?.let {
            viewModel.onEvent(BankDetailsEvent.OnFileSelected(it))
        }
    }

    // -------- Camera Launcher --------
    val cameraLauncher = FilePickerUtils.rememberCameraLauncher {
        cameraTempUri?.let {
            viewModel.onEvent(BankDetailsEvent.OnFileSelected(it))
        }
    }

    // -------- Camera Permission --------
    val cameraPermissionLauncher =
        PermissionUtils.rememberCameraPermissionLauncher { granted ->

            if (granted) {

                cameraTempUri = FilePickerUtils.createTempImageUri()
                cameraLauncher.launch(cameraTempUri!!)

            } else {
                appState.showSnackBar("Camera permission denied")
            }
        }

    // -------- Snackbar observer --------
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect {
            appState.showSnackBar(it)
        }
    }

    // -------- Navigation observer --------
    LaunchedEffect(Unit) {
        viewModel.navigation.collect { event ->
            when (event) {
                is NavigationEvent.PopBack -> appState.navigator.popBack()
                is NavigationEvent.Navigate -> appState.navigator.navigate(event.route)
                else -> {}
            }
        }
    }

    Box(Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {

            Spacer(Modifier.height(220.dp))

            Column(
                Modifier
                    .fillMaxWidth()
                    .background(AppGradients.lightPrimaryBackground(Primary))
                    .padding(16.dp)
            ) {

                AppTextFieldWithLabel(
                    labelText = "Dummy Input Field",
                    value = uiState.accountNumber,
                    hint = "Enter",
                    keyboardType = KeyboardType.Number,
                    onValueChange = {
                        viewModel.onEvent(
                            BankDetailsEvent.OnAccountNumberChanged(it)
                        )
                    }
                )

                Spacer(Modifier.height(20.dp))

                AppCustomRadioGroup(
                    labelText = "Dummy Radio Button Field",
                    options = viewModel.options,
                    selectedId = uiState.radioSelection,
                    onOptionSelected = {
                        viewModel.onEvent(
                            BankDetailsEvent.OnRadioChanged(it)
                        )
                    }
                )

                Spacer(Modifier.height(20.dp))

                AppDatePickerWithLabel(
                    labelText = "Dummy Date Picker",
                    hint = "Select Date",
                    selectedDate = uiState.date,
                    onDateSelected = {
                        viewModel.onEvent(
                            BankDetailsEvent.OnDateSelected(it)
                        )
                    }
                )

                Spacer(Modifier.height(20.dp))

                AppDropdownWithLabel(
                    labelText = "Dummy Dropdown Fields",
                    hint = "Select",
                    itemList = listOf("Option 1","Option 2","Option 3"),
                    selectedItem = uiState.dropdown,
                    onSelectedItem = {
                        viewModel.onEvent(
                            BankDetailsEvent.OnDropdownChanged(it)
                        )
                    }
                )

                Spacer(Modifier.height(20.dp))

                AppUploadAadharField(

                    labelText = "Upload Bank Document",

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
                            BankDetailsEvent.OnFileSelected("")
                        )
                    }
                )

                Spacer(Modifier.height(80.dp))
            }
        }

        // ---------- Header ----------
        Box(
            Modifier
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(onClick = {
                    viewModel.onEvent(BankDetailsEvent.OnBackClick)
                }) {

                    Icon(
                        painter = painterResource(Res.drawable.ic_arrow_back),
                        contentDescription = null,
                        tint = Color.White
                    )
                }

                Text(
                    text = "Bank Details",
                    color = Color.White,
                    style = TextStyles.InterSemiBoldM
                )
            }
        }

        // ---------- Submit Button ----------
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
                        BankDetailsEvent.OnSubmitClick
                    )
                }
            )
        }
        if (showViewer) {
            AppCustomFileViewer(
                headText = "View Bank Document",
                fileUri = uiState.fileUri,
                onDismiss = { showViewer = false }
            )
        }

        // ---------- BottomSheet ----------
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
}
