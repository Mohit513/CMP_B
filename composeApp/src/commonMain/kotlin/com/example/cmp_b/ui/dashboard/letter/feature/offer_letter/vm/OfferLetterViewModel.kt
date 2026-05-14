package com.example.cmp_b.ui.dashboard.letter.feature.offer_letter.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class OfferLetterViewModel(): ViewModel(){
    private val _uiState = MutableStateFlow(OfferLetterUiState())
    val uiState : StateFlow<OfferLetterUiState> = _uiState

    private val _offers = MutableStateFlow(
        listOf(
            OfferLetterUiState(
                id = "1",
                companyName = "Satvik Solar industries",
                role = "Assistant Manager",
                candidateName = "Ram Sharma",
                joiningDate = "12 March, 2025",
                pdfUrl = "dummy.pdf"
            ),
            OfferLetterUiState(
                id = "2",
                companyName = "Satvik Solar industries",
                role = "Assistant Manager",
                candidateName = "Ram Sharma",
                joiningDate = "12 March, 2025",
                pdfUrl = "dummy.pdf"
            )
        )
    )

    val offers = _offers.asStateFlow()

    var showSignatureSheet by mutableStateOf(false)

    var showReasonSheet by mutableStateOf(false)

    var selectedOfferId by mutableStateOf<String?>(null)
        private set

    fun onAccept(id: String) {
        updateStatus(id, OfferStatus.ACCEPTED)
    }

    fun onReject(id: String) {
        updateStatus(id, OfferStatus.REJECTED)
    }

    fun onAcceptClick(id: String) {
        selectedOfferId = id
        showSignatureSheet = true
    }

    fun onRejectClick(id: String) {
        selectedOfferId = id
        showReasonSheet = true
    }

    fun submitSignature() {
        selectedOfferId?.let { updateStatus(it, OfferStatus.ACCEPTED) }
        showSignatureSheet = false
    }

    fun submitReason() {
        selectedOfferId?.let { updateStatus(it, OfferStatus.REJECTED) }
        showReasonSheet = false
    }

    private fun updateStatus(id: String, status: OfferStatus) {
        _offers.update { list ->
            list.map {
                if (it.id == id) it.copy(status = status) else it
            }
        }
    }

}
enum class OfferStatus {
    AWAITING, ACCEPTED, REJECTED
}

data class OfferLetterUiState(
    val id: String = "",
    val companyName: String = "",
    val role: String = "",
    val candidateName: String =  "",
    val joiningDate: String="",
    val pdfUrl: String= "",
    val status: OfferStatus = OfferStatus.AWAITING
)