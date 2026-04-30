package com.example.cmp_b.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmp_b.navigation.NavRoutes
import com.example.cmp_b.navigation.NavigationEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

enum class AuthFlow { LOGIN, ONBOARDING }

data class AuthUiState(
    val mobile: String = "",
    val otp: String? = "",
    val isLoading: Boolean = false,
    val isOtpSent: Boolean = false,
    val renewSeconds: Int = 59,
    val isMobileValid: Boolean = true,
    val isOtpValid: Boolean = true,
    val authFlow: AuthFlow = AuthFlow.LOGIN,
)

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState

    private val _uiEvent = Channel<String>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _navigation = Channel<NavigationEvent>()
    val navigation = _navigation.receiveAsFlow()

    private var timerJob: Job? = null

    fun onMobileChanged(value: String) {
        if (!value.all { it.isDigit() }) return
        if (value.length > 10) return

        _uiState.update {
            it.copy(
                mobile = value,
                isMobileValid = true
            )
        }
    }

    fun onOtpChange(newOtp: String) {
        if (newOtp.length <= 4 && newOtp.all { it.isDigit() }) {
            _uiState.update {
                it.copy(otp = newOtp, isOtpValid = true)
            }
        }
    }

    fun onLoginClick() {
        val mobile = _uiState.value.mobile
        if (mobile.length != 10) {
            _uiState.update { it.copy(isMobileValid = false) }
            viewModelScope.launch { _uiEvent.send("Please enter a valid 10-digit mobile number") }
            return
        }

        // Simulating API call
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            delay(1500)
            _uiState.update {
                it.copy(
                    isLoading = false,
                    isOtpSent = true,
                    authFlow = AuthFlow.LOGIN
                )
            }
            startOtpTimer()
            _uiEvent.send("OTP Sent Successfully")
        }
    }

    fun onOnBoardingClick() {
        onLoginClick() // Similar logic for dummy
    }

    fun onOtpSubmit() {
        val otp = _uiState.value.otp
        if (otp?.length != 4) {
            _uiState.update { it.copy(isOtpValid = false) }
            viewModelScope.launch { _uiEvent.send("Invalid OTP") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            delay(1000)
            _uiState.update { it.copy(isLoading = false) }
            _navigation.send(NavigationEvent.ClearBackStackAndNavigate(NavRoutes.PostList.route))
        }
    }

    fun goBackToLogin() {
        _uiState.update { it.copy(isOtpSent = false, otp = "") }
    }

    fun onResendOtp() {
        startOtpTimer()
        viewModelScope.launch { _uiEvent.send("OTP Resent") }
    }

    private fun startOtpTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            for (i in 30 downTo 0) {
                _uiState.update { it.copy(renewSeconds = i) }
                delay(1000)
            }
        }
    }

    override fun onCleared() {
        timerJob?.cancel()
        super.onCleared()
    }
}
