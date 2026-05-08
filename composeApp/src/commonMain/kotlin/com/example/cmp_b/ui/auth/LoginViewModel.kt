package com.example.cmp_b.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmp_b.PlatformInfo
import com.example.cmp_b.core.data.network.auth.CandidateLoginRequestDto
import com.example.cmp_b.core.data.network.auth.LoginOtpValidateRequestDto
import com.example.cmp_b.core.data.session.SessionManager
import com.example.cmp_b.core.data.network.api.common.NetworkResult
import com.example.cmp_b.navigation.NavRoutes
import com.example.cmp_b.navigation.NavigationEvent
import com.example.cmp_b.shared.domain.model.auth.CandidateLoginUiModel
import com.example.cmp_b.shared.domain.usecase.CandidateLoginUseCase
import com.example.cmp_b.shared.domain.usecase.LoginOtpValidateUseCase
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
    val isOtpRequested: Boolean = false,
    val openOnboarding: Boolean = false,
    val authFlow: AuthFlow = AuthFlow.LOGIN,
)

class LoginViewModel(
    private val platformInfo: PlatformInfo,
    private val loginUseCase: CandidateLoginUseCase,
    private val otpValidateUseCase: LoginOtpValidateUseCase,
    private val sessionManager: SessionManager,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    private var lastSentMobile: String? = null
    private var timerJob: Job? = null

    private val _uiEvent = Channel<String>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _navigation = Channel<NavigationEvent>()
    val navigation = _navigation.receiveAsFlow()

    private fun sendNavigation(event: NavigationEvent) {
        viewModelScope.launch { _navigation.send(event) }
    }

    private fun showSnackBar(message: String) {
        viewModelScope.launch { _uiEvent.send(message) }
    }

    fun goBackToLogin() {
        _uiState.update {
            it.copy(
                isOtpSent = false,
                otp = ""
            )
        }
    }

    fun onMobileChanged(value: String) {
        if (!value.all { it.isDigit() }) return
        if (value.length > 10) return

        val isValid = when {
            value.isEmpty() -> false
            value.length < 10 -> false
            isSameDigits(value) -> false
            else -> true
        }

        _uiState.update {
            it.copy(
                mobile = value,
                isMobileValid = isValid
            )
        }
    }

    fun onOtpChange(newOtp: String) {
        if (newOtp.length <= 6 && newOtp.all { it.isDigit() }) {
            _uiState.update {
                it.copy(
                    otp = newOtp,
                    isOtpValid = true
                )
            }
        }
    }

    private fun isValidMobile(m: String): Boolean {
        if (m.length != 10) return false
        if (m.firstOrNull() !in listOf('6', '7', '8', '9')) return false
        if (isSameDigits(m)) return false
        return true
    }

    fun onLoginClick() {
        val mobile = _uiState.value.mobile

        if (!isValidMobile(mobile)) {
            _uiState.update { it.copy(isMobileValid = false) }
            showSnackBar("Please enter valid mobile number")
            return
        }
        lastSentMobile = mobile

        viewModelScope.launch {
            loginUseCase.callCandidateLoginApi(
                CandidateLoginRequestDto(
                    apkVersion = platformInfo.appVersion,
                    androidVersion = platformInfo.osVersion,
                    buildNo = platformInfo.buildNumber,
                    employeeCode = "",
                    mobile = mobile,
                    modelNo = platformInfo.deviceModel,
                    signupSource = "D"
                )
            ).collect { result ->
                callLoginApi(result = result, flow = AuthFlow.LOGIN)
            }
        }
    }

    private fun callLoginApi(result: NetworkResult<CandidateLoginUiModel>, flow: AuthFlow) {
        when (result) {
            is NetworkResult.Loading -> {
                _uiState.update { it.copy(isLoading = true) }
            }

            is NetworkResult.Success -> {
                val data = result.data
                if (data.candidateStatus.equals("Valid", ignoreCase = true)) {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isOtpSent = true,
                            authFlow = flow
                        )
                    }
                    startOtpTimer()
                    showSnackBar("OTP Send Successfully")
                } else {
                    _uiState.update { it.copy(isLoading = false) }
                    showSnackBar(data?.status ?: "Invalid user or not allowed")
                }
            }

            is NetworkResult.Error -> {
                _uiState.update { it.copy(isLoading = false) }
                showSnackBar(result.message ?: "Login failed")
            }
        }
    }

    fun onOnBoardingClick() {
        val mobile = _uiState.value.mobile
        val valid = isValidMobile(mobile)

        if (!valid) {
            _uiState.update { it.copy(isMobileValid = false) }
            showSnackBar("Please enter valid mobile number")
            return
        }

        viewModelScope.launch {
            loginUseCase.callCandidateLoginApi(
                CandidateLoginRequestDto(
                    apkVersion = platformInfo.appVersion,
                    androidVersion = platformInfo.osVersion,
                    buildNo = platformInfo.buildNumber,
                    employeeCode = "",
                    mobile = mobile,
                    modelNo = platformInfo.deviceModel,
                    signupSource = "D"
                )
            ).collect { result ->
                callLoginApi(result, AuthFlow.ONBOARDING)
            }
        }
    }

    fun onOtpSubmit() {
        val otp = _uiState.value.otp
        val mobile = _uiState.value.mobile

        if (otp?.length != 4) {
            _uiState.update { it.copy(isOtpValid = false) }
            showSnackBar("Please enter valid OTP")
            return
        }

        viewModelScope.launch {
            otpValidateUseCase.callLoginOtpValidateApi(
                request = LoginOtpValidateRequestDto(
                    mobile = mobile ?: "",
                    otp = otp
                )
            ).collect { result ->
                when (result) {
                    is NetworkResult.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }

                    is NetworkResult.Success -> {
                        _uiState.update { it.copy(isLoading = false) }
                        val data = result.data

                        if (data?.otpStatus.equals("Valid", true)) {
                            // Persist session so user skips login on next app launch
                            sessionManager.authToken = data?.tokenID
                            sessionManager.userMobile = mobile
                            sessionManager.isLoggedIn = true

                            sendNavigation(
                                NavigationEvent.ClearBackStackAndNavigate(
                                    NavRoutes.DigiDashboard.route
                                )
                            )
                        } else {
                            showSnackBar("Invalid OTP")
                        }
                    }

                    is NetworkResult.Error -> {
                        _uiState.update { it.copy(isLoading = false) }
                        showSnackBar(result.message ?: "Something went wrong")
                    }
                }
            }
        }
    }

    fun onResendOtp() {
        val mobile = lastSentMobile ?: _uiState.value.mobile.trim()

        if (mobile.isEmpty() || !isValidMobile(mobile)) {
            showSnackBar("No valid mobile number for resend")
            return
        }

        if (_uiState.value.isLoading) return

        viewModelScope.launch {
            loginUseCase.callCandidateLoginApi(
                CandidateLoginRequestDto(
                    apkVersion = platformInfo.appVersion,
                    androidVersion = platformInfo.osVersion,
                    buildNo = platformInfo.buildNumber,
                    employeeCode = "",
                    mobile = mobile,
                    modelNo = platformInfo.deviceModel,
                    signupSource = "D"
                )
            ).collect { result ->
                callLoginApi(result, flow = AuthFlow.LOGIN)
            }
        }
    }

    fun startOtpTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            for (i in 59 downTo 0) {
                _uiState.update { it.copy(renewSeconds = i) }
                delay(1000)
            }
        }
    }

    private fun isSameDigits(number: String): Boolean {
        return number.toSet().size == 1
    }

    override fun onCleared() {
        timerJob?.cancel()
        super.onCleared()
    }
}
