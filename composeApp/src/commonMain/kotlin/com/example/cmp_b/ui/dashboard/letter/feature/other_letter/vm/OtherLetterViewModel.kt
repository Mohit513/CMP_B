package com.example.cmp_b.ui.dashboard.letter.feature.other_letter.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OtherLetterViewModel(): ViewModel() {

    private val _letters = MutableStateFlow<List<OtherLetterUiState>>(emptyList())

    val letters: StateFlow<List<OtherLetterUiState>> = _letters.asStateFlow()

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        // Simulating data fetch (Replace this with your API call later)
        val mockData = listOf(
            OtherLetterUiState(
                id = "1",
                title = "Contract Extension Letter",
                remark = "Lorem Ipsum Dolor Sit Amet, Consectetur Adipis",
                generatedOn = "21 Nov, 2025",
                status = OtherLetterStatus.PENDING,
                fileName = "Contract Extension Letter.Pdf"
            ),
            OtherLetterUiState(
                id = "2",
                title = "Contract Extension Letter",
                remark = "Lorem Ipsum Dolor Sit Amet, Consectetur Adipis",
                generatedOn = "21 Nov, 2025",
                status = OtherLetterStatus.ACCEPTED,
                fileName = "Contract Extension Letter.Pdf"
            )
        )
        _letters.value = mockData
    }

    // 4. Handle Accept Click
    fun onAcceptClick(letterId: String) {
        _letters.update { currentList ->
            currentList.map { letter ->
                if (letter.id == letterId) {
                    letter.copy(status = OtherLetterStatus.ACCEPTED)
                } else {
                    letter
                }
            }
        }
    }

    // 5. Handle Download Click
    fun onDownloadClick(fileName: String) {

    }
}

data class OtherLetterUiState(
    val id: String,
    val title: String = "Contract Extension Letter",
    val remark: String = "Lorem Ipsum Dolor Sit Amet, Consectetur Adipis",
    val generatedOn: String = "21 Nov, 2025",
    val status: OtherLetterStatus = OtherLetterStatus.PENDING,
    val fileName: String = "Contract Extension Letter.Pdf"
)

enum class OtherLetterStatus { PENDING, ACCEPTED }