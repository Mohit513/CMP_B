package com.example.cmp_b.ui.dashboard.onboarding.sub_screens.aadhar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmp_b.navigation.NavigationEvent
import com.example.cmp_b.ui.components.RadioOption
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AadharDetailsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AadharDetailsUiState())
    val uiState: StateFlow<AadharDetailsUiState> = _uiState

    private val _uiEvent = Channel<String>()
    val uiEvent = _uiEvent.receiveAsFlow()

    // navigation events
    private val _navigation = Channel<NavigationEvent>()
    val navigation = _navigation.receiveAsFlow()

    fun onEvent(event: AadharDetailsEvent) {

        when (event) {

            is AadharDetailsEvent.OnNameChanged -> {
                if (event.value.length > 50) return

                _uiState.update {
                    it.copy(name = event.value)
                }
            }

            is AadharDetailsEvent.OnAadharChanged -> {

                val digits = event.value.filter { it.isDigit() }.take(12)

                val error = when {
                    digits.isEmpty() -> "Aadhar required"
                    digits.length < 12 -> "Enter valid Aadhar"
                    else -> null
                }

                _uiState.update {
                    it.copy(
                        aadharNumber = digits,
                        aadharError = error
                    )
                }
            }

            is AadharDetailsEvent.OnMobileChanged -> {

                if (!event.value.all { it.isDigit() }) return
                if (event.value.length > 10) return

                val error = when {
                    event.value.isEmpty() -> "Mobile required"
                    event.value.length < 10 -> "Enter 10 digit mobile"
                    event.value.firstOrNull() !in listOf('6','7','8','9') -> "Mobile must start with 6-9"
                    else -> null
                }

                _uiState.update {
                    it.copy(
                        mobileNumber = event.value,
                        mobileError = error
                    )
                }
            }

            is AadharDetailsEvent.OnFatherNameChanged -> {

                if (event.value.length > 50) return

                _uiState.update {
                    it.copy(fatherName = event.value)
                }
            }

            is AadharDetailsEvent.OnGenderChanged -> {
                _uiState.update {
                    it.copy(gender = event.value.toInt())
                }
            }

            is AadharDetailsEvent.OnDobSelected -> {
                _uiState.update {
                    it.copy(dob = event.value)
                }
            }

            is AadharDetailsEvent.OnDropdownSelected -> {
                _uiState.update {
                    it.copy(selectedDropdown = event.value)
                }
            }

            is AadharDetailsEvent.OnFileSelected -> {

                if (event.uri.isEmpty()) {

                    _uiState.update {
                        it.copy(
                            fileUri = null,
                            fileName = null,
                            fileSize = null
                        )
                    }

                } else {

                    _uiState.update {
                        it.copy(
                            fileUri = event.uri,
                            fileName = "Aadhar.jpg",
                            fileSize = "2 MB"
                        )
                    }
                }
            }

            AadharDetailsEvent.OnSubmitClick -> {

                if (!validateFields()) return

                viewModelScope.launch {
                    _navigation.send(
                        NavigationEvent.Navigate("next_screen")
                    )
                }
            }

            AadharDetailsEvent.OnBackClick -> {

                viewModelScope.launch {
                    _navigation.send(NavigationEvent.PopBack)
                }
            }
        }
    }

    val options = listOf(
        RadioOption(1, "Male"),
        RadioOption(2, "Female"),
    )

    private fun showSnackBar(message: String) {
        viewModelScope.launch {
            _uiEvent.send(message)
        }
    }

    private fun validateFields(): Boolean {

        val state = _uiState.value

        if (state.name.isBlank()) {
            showSnackBar("Enter name as per Aadhaar")
            return false
        }

        if (state.aadharNumber.isEmpty()) {
            showSnackBar("Aadhar required")
            return false
        }

        if (state.aadharNumber.length != 12) {
            showSnackBar("Enter valid Aadhar number")
            return false
        }

        if (state.mobileNumber.isEmpty() ||
            state.mobileNumber.firstOrNull() !in listOf('6','7','8','9')
        ) {
            showSnackBar("Enter valid mobile number starting with 6-9")
            return false
        }

        if (state.mobileNumber.length != 10) {
            showSnackBar("Enter valid mobile number")
            return false
        }

        if (state.dob.isBlank()) {
            showSnackBar("Select date of birth")
            return false
        }

        if (state.fileUri == null) {
            showSnackBar("Upload Aadhaar card")
            return false
        }

        return true
    }
}
data class AadharDetailsUiState(

    val isLoading: Boolean = false,

    val name: String = "",
    val aadharNumber: String = "",
    val gender: Int = 1,
    val dob: String = "",
    val mobileNumber: String = "",
    val fatherName: String = "",

    val selectedDropdown: String = "",

    val fileUri: String? = null,
    val fileName: String? = null,
    val fileSize: String? = null,

    val nameError: String? = null,
    val aadharError: String? = null,
    val mobileError: String? = null
)

sealed class AadharDetailsEvent {

    data class OnNameChanged(val value: String) : AadharDetailsEvent()

    data class OnAadharChanged(val value: String) : AadharDetailsEvent()

    data class OnMobileChanged(val value: String) : AadharDetailsEvent()

    data class OnFatherNameChanged(val value: String) : AadharDetailsEvent()

    data class OnGenderChanged(val value: String) : AadharDetailsEvent()

    data class OnDobSelected(val value: String) : AadharDetailsEvent()

    data class OnDropdownSelected(val value: String) : AadharDetailsEvent()

    data class OnFileSelected(val uri: String) : AadharDetailsEvent()

    object OnSubmitClick : AadharDetailsEvent()

    object OnBackClick : AadharDetailsEvent()
}