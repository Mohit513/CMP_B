package com.example.cmp_b.ui.dashboard.document_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cmp_b.composeapp.generated.resources.Res
import cmp_b.composeapp.generated.resources.ic_eye_svg
import com.example.cmp_b.navigation.AppState
import com.example.cmp_b.ui.bottomsheet.ImageSourceBottomSheet
import com.example.cmp_b.ui.components.AppCustomButton
import com.example.cmp_b.ui.components.AppCustomDialog
import com.example.cmp_b.ui.components.AppCustomFileViewer
import com.example.cmp_b.ui.components.AppOnBoardingListItem
import com.example.cmp_b.ui.components.AppSimpleTopBar
import com.example.cmp_b.ui.dashboard.document_details.vm.DocumentDetailsEvent
import com.example.cmp_b.ui.dashboard.document_details.vm.DocumentDetailsViewModel
import com.example.cmp_b.ui.theme.AppGradients
import com.example.cmp_b.util.FilePickerUtils
import com.example.cmp_b.util.PermissionUtils
import com.example.cmp_b.util.openAppSettings
import com.example.cmp_b.util.getCurrentTimestamp
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentDetailsScreen(
    appState: AppState,
    viewModel: DocumentDetailsViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    var showAddSheet by remember { mutableStateOf(false) }
    var showSourceSheet by remember { mutableStateOf(false) }
    var showViewer by remember { mutableStateOf(false) }
    var selectedDocUri by remember { mutableStateOf<String?>(null) }
    var cameraTempUri by remember { mutableStateOf<String?>(null) }
    var showPermissionDialog by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState()

    val cameraLauncher = FilePickerUtils.rememberCameraLauncher { success ->
        if (success) {
            cameraTempUri?.let { uri ->
                viewModel.onEvent(
                    DocumentDetailsEvent.OnFileSelected(
                        uri,
                        "Camera_${getCurrentTimestamp()}.jpg",
                        FilePickerUtils.formatFileSize(FilePickerUtils.getFileSize(uri))
                    )
                )
            }
        }
    }

    val cameraPermissionLauncher = PermissionUtils.rememberCameraPermissionLauncher { granted ->
        if (granted) {
            cameraTempUri = FilePickerUtils.createTempImageUri()
            cameraLauncher.launch()
        } else {
            showPermissionDialog = true
        }
    }

    val galleryLauncher = FilePickerUtils.rememberGalleryLauncher { uri ->
        uri?.let {
            viewModel.onEvent(
                DocumentDetailsEvent.OnFileSelected(
                    it,
                    FilePickerUtils.getFileName(it),
                    FilePickerUtils.formatFileSize(FilePickerUtils.getFileSize(it))
                )
            )
        }
    }

    val documentLauncher = FilePickerUtils.rememberDocumentLauncher { uri ->
        uri?.let {
            viewModel.onEvent(
                DocumentDetailsEvent.OnFileSelected(
                    it,
                    FilePickerUtils.getFileName(it),
                    FilePickerUtils.formatFileSize(FilePickerUtils.getFileSize(it))
                )
            )
        }
    }

    Scaffold(
        topBar = {
            AppSimpleTopBar(
                title = "Document Details",
                onBackClick = { appState.navigator.popBack() }
            )
        },
        bottomBar = {
            AppCustomButton(
                firstButtonText = "+ Add New Document",
                onFirstButtonClick = { 
                    viewModel.onEvent(DocumentDetailsEvent.OnClearFile)
                    showAddSheet = true 
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(AppGradients.lightPrimaryBackground())
        ) {
            items(uiState.documents) { doc ->
                AppOnBoardingListItem(
                    title = doc.title,
                    imageRes = doc.icon,
                    icon = Res.drawable.ic_eye_svg,
                    onItemClick = {
                        if (doc.uri.isNotEmpty()) {
                            selectedDocUri = doc.uri
                            showViewer = true
                        } else {
                            appState.showSnackBar("No file attached to ${doc.title}")
                        }
                    },
                    onIconClick = {
                        if (doc.uri.isNotEmpty()) {
                            selectedDocUri = doc.uri
                            showViewer = true
                        } else {
                            appState.showSnackBar("No file attached to ${doc.title}")
                        }
                    }
                )
            }
        }

        // Add Document Bottom Sheet
        if (showAddSheet) {
            AddDocumentBottomSheet(
                sheetState = sheetState,
                uiState = uiState,
                onDismiss = { showAddSheet = false },
                onEvent = { event ->
                    viewModel.onEvent(event)
                    if (event is DocumentDetailsEvent.OnSubmitClick) {
                        showAddSheet = false
                    }
                },
                onPickFileClick = { showSourceSheet = true },
                onViewFileClick = {
                    uiState.selectedFileUri?.let {
                        selectedDocUri = it
                        showViewer = true
                    }
                }
            )
        }

        // Source Selection Sheet
        if (showSourceSheet) {
            ImageSourceBottomSheet(
                onCameraSelected = {
                    showSourceSheet = false
                    if (PermissionUtils.hasCameraPermission()) {
                        cameraTempUri = FilePickerUtils.createTempImageUri()
                        cameraLauncher.launch()
                    } else {
                        cameraPermissionLauncher.launch("camera")
                    }
                },
                onGallerySelected = {
                    showSourceSheet = false
                    galleryLauncher.launch("image/*")
                },
                onFileSelected = {
                    showSourceSheet = false
                    documentLauncher.launch(arrayOf("*/*"))
                },
                onDismiss = { showSourceSheet = false }
            )
        }

        // File Viewer
        if (showViewer && selectedDocUri != null) {
            AppCustomFileViewer(
                headText = "View Document",
                fileUri = selectedDocUri!!,
                onDismiss = { 
                    showViewer = false 
                    selectedDocUri = null
                }
            )
        }

        // Permission Dialog
        if (showPermissionDialog) {
            AppCustomDialog(
                showDialog = true,
                title = "Permission Required",
                message = "Camera permission is required to take photos. Please enable it in settings.",
                confirmText = "Open Settings",
                dismissText = "Cancel",
                onConfirm = {
                    openAppSettings()
                    showPermissionDialog = false
                },
                onDismiss = { showPermissionDialog = false }
            )
        }
    }
}
