package com.example.cmp_b.ui.dashboard.document_details.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cmp_b.composeapp.generated.resources.Res
import cmp_b.composeapp.generated.resources.ic_kyc
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import com.example.cmp_b.util.getCurrentTimestamp

class DocumentDetailsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DocumentDetailsUiState())
    val uiState: StateFlow<DocumentDetailsUiState> = _uiState.asStateFlow()

    init {
        // Initial dummy data
        _uiState.update {
            it.copy(
                documents = listOf(
                    DocumentItem("1", "Aadhar Card", icon = Res.drawable.ic_kyc, ""),
                    DocumentItem("2", "HSC Certificate", Res.drawable.ic_kyc, ""),
                    DocumentItem("3", "SSC Certificate", Res.drawable.ic_kyc, "")
                ),
                availableDocumentTypes = listOf(
                    "Aadhar Card",
                    "HSC Certificate",
                    "SSC Certificate",
                    "PAN Card",
                    "Voter ID",
                    "Passport",
                    "Driving License"
                )
            )
        }
    }

    fun onEvent(event: DocumentDetailsEvent) {
        when (event) {
            is DocumentDetailsEvent.OnDocumentTypeSelected -> {
                _uiState.update { it.copy(selectedDocumentType = event.documentType) }
            }

            is DocumentDetailsEvent.OnFileSelected -> {
                _uiState.update {
                    it.copy(
                        selectedFileUri = event.uri,
                        selectedFileName = event.fileName,
                        selectedFileSize = event.fileSize
                    )
                }
            }

            DocumentDetailsEvent.OnClearFile -> {
                _uiState.update {
                    it.copy(
                        selectedFileUri = null,
                        selectedFileName = null,
                        selectedFileSize = null
                    )
                }
            }

            DocumentDetailsEvent.OnSubmitClick -> {
                val state = _uiState.value

                if (state.selectedDocumentType.isNotEmpty() && state.selectedFileUri != null) {
                    val timestamp = getCurrentTimestamp()
                    val newDocument = DocumentItem(
                        id = timestamp.toString(),
                        title = state.selectedDocumentType,
                        icon = Res.drawable.ic_kyc,
                        uri = state.selectedFileUri
                    )

                    _uiState.update {
                        it.copy(
                            documents = it.documents + newDocument,
                            selectedDocumentType = "",
                            selectedFileUri = null,
                            selectedFileName = null,
                            selectedFileSize = null,
                            isDocumentAdded = true
                        )
                    }
                }
            }

            DocumentDetailsEvent.ResetAddedStatus -> {
                _uiState.update { it.copy(isDocumentAdded = false) }
            }
        }
    }
}

data class DocumentDetailsUiState(
    val documents: List<DocumentItem> = emptyList(),
    val availableDocumentTypes: List<String> = emptyList(),
    val selectedDocumentType: String = "",
    val selectedFileUri: String? = null,
    val selectedFileName: String? = null,
    val selectedFileSize: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isDocumentAdded: Boolean = false
)

data class DocumentItem(
    val id: String,
    val title: String,
    val icon: DrawableResource,
    val uri: String
)

sealed class DocumentDetailsEvent {
    data class OnDocumentTypeSelected(val documentType: String) : DocumentDetailsEvent()
    data class OnFileSelected(val uri: String, val fileName: String, val fileSize: String) : DocumentDetailsEvent()
    object OnClearFile : DocumentDetailsEvent()
    object OnSubmitClick : DocumentDetailsEvent()
    object ResetAddedStatus : DocumentDetailsEvent()
}
