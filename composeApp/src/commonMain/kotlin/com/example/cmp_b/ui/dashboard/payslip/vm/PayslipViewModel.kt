package com.example.cmp_b.ui.dashboard.payslip.vm

import androidx.lifecycle.ViewModel
import com.example.cmp_b.navigation.NavigationEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update


class PayslipViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PayslipUiState())
    val uiState: StateFlow<PayslipUiState> = _uiState.asStateFlow()

    private val _navigation = Channel<NavigationEvent>()
    val navigation = _navigation.receiveAsFlow()

    private val _event = Channel<PayslipEvent>()
    val uiEvent = _event.receiveAsFlow()

    init {
        _uiState.update {
            it.copy(
                years = listOf("2023", "2024", "2025", "2026"), months = listOf(
                    MonthStatus("January", false),
                    MonthStatus("February", false),
                    MonthStatus("March", false),
                    MonthStatus("April", true),
                    MonthStatus("May", true),
                    MonthStatus("June", true),
                    MonthStatus("July", true),
                    MonthStatus("August", true),
                    MonthStatus("September", true),
                    MonthStatus("October", true),
                    MonthStatus("November", true),
                    MonthStatus("December", true)
                )
            )
        }
    }

    fun onEvent(event: PayslipEvent) {
        when (event) {
            is PayslipEvent.OnYearClick -> {
                _uiState.update { it.copy(selectedYear = event.year) }
            }

            PayslipEvent.OnBackClick -> {
                // Handle back navigation
            }

            is PayslipEvent.OnDownloadClick -> {
                // Handle download
            }
        }
    }
}

data class PayslipUiState(
    val selectedYear: String = "2024",
    val years: List<String> = listOf(),
    val months: List<MonthStatus> = listOf(),
    val error: String? = null,
)

data class MonthStatus(
    val month: String, val isAvailable: Boolean
)

sealed class PayslipEvent {
    object OnBackClick : PayslipEvent()
    data class OnYearClick(val year: String) : PayslipEvent()
    data class OnDownloadClick(val month: String) : PayslipEvent()
}
