package com.example.cmp_b.ui.dashboard.document_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cmp_b.ui.components.AppCustomButton
import com.example.cmp_b.ui.components.AppDropdownWithLabel
import com.example.cmp_b.ui.components.AppUploadAadharField
import com.example.cmp_b.ui.theme.Alto_5
import com.example.cmp_b.ui.theme.TextStyles
import com.example.cmp_b.ui.dashboard.document_details.vm.DocumentDetailsEvent
import com.example.cmp_b.ui.dashboard.document_details.vm.DocumentDetailsUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDocumentBottomSheet(
    sheetState: SheetState,
    uiState: DocumentDetailsUiState,
    onDismiss: () -> Unit,
    onEvent: (DocumentDetailsEvent) -> Unit,
    onPickFileClick: () -> Unit,
    onViewFileClick: () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        modifier = Modifier.padding(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text("Add New Document", textAlign = TextAlign.Center, style = TextStyles.InterBoldM)

            HorizontalDivider(thickness = 1.dp, color = Alto_5, modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp))

            AppDropdownWithLabel(
                labelText = "Select Document",
                hint = "Select Document",
                itemList = uiState.availableDocumentTypes,
                selectedItem = uiState.selectedDocumentType,
                onSelectedItem = {
                    onEvent(DocumentDetailsEvent.OnDocumentTypeSelected(it))
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            //  Upload Field
            AppUploadAadharField(
                titleText = "Upload Document",
                labelText = "",
                fileName = uiState.selectedFileName,
                fileSize = uiState.selectedFileSize,
                onUploadClick = { onPickFileClick() },
                onViewClick = { onViewFileClick() },
                onRemoveClick = { onEvent(DocumentDetailsEvent.OnClearFile) }
            )

            Spacer(modifier = Modifier.height(24.dp))

            AppCustomButton(
                firstButtonText = "Submit",
                onFirstButtonClick = {
                    onEvent(DocumentDetailsEvent.OnSubmitClick)
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
