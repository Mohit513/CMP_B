package com.example.cmp_b.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.example.cmp_b.navigation.AppState
import com.example.cmp_b.navigation.NavigationEvent
import com.example.cmp_b.ui.components.*
import cmp_b.composeapp.generated.resources.Res
import cmp_b.composeapp.generated.resources.image_top_login

@Composable
fun LoginScreen(
    appState: AppState,
    loginViewModel: LoginViewModel
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val uiState by loginViewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    // Snackbar Events
    LaunchedEffect(Unit) {
        loginViewModel.uiEvent.collect { message ->
            appState.showSnackBar(message)
        }
    }

    // Navigation Events
    LaunchedEffect(Unit) {
        loginViewModel.navigation.collect { event ->
            when (event) {
                is NavigationEvent.Navigate -> appState.navigator.navigate(event.route)
                is NavigationEvent.PopBack -> appState.navigator.popBack()
                is NavigationEvent.PopBackWithResult -> appState.navigator.popBackWithResult(event.key, event.value)
                is NavigationEvent.ClearBackStackAndNavigate -> appState.navigator.clearAndNavigate(event.route)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
            .verticalScroll(scrollState)
    ) {
        AuthHeaderImage(
            imageRes = Res.drawable.image_top_login,
            title = if (!uiState.isOtpSent) "Login 👋" else "OTP Verification",
            subtitleContent = {
                if (!uiState.isOtpSent) {
                    Text(
                        text = "Enter your login details to proceed to the home page.",
                        style = TextStyles.InterRegularS,
                        color = BackgroundLight,
                    )
                } else {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Enter OTP sent to the number ",
                            style = TextStyles.InterRegularXS,
                            color = BackgroundLight
                        )
                        Text(
                            text = uiState.mobile,
                            style = TextStyles.InterSemiBoldXS,
                            color = BackgroundLight
                        )
                    }
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 34.dp, horizontal = 16.dp)
        ) {
            if (uiState.isLoading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp))
            }

            if (!uiState.isOtpSent) {
                AppTextFieldWithLabel(
                    labelText = "Mobile Number",
                    hint = "Enter Mobile Number",
                    value = uiState.mobile,
                    onValueChange = {
                        loginViewModel.onMobileChanged(it)
                        if (it.length == 10) keyboardController?.hide()
                    },
                    isError = !uiState.isMobileValid
                )

                Spacer(modifier = Modifier.height(100.dp))

                AppMultipleButtons(
                    modifier = Modifier.fillMaxWidth(),
                    firstButtonText = "Login",
                    secondButtonText = "Start Onboarding",
                    onFirstButtonClick = { loginViewModel.onLoginClick() },
                    onSecondButtonClick = { loginViewModel.onOnBoardingClick() }
                )
            } else {
                AppOtpBoxes(
                    value = uiState.otp ?: "",
                    onValueChange = { loginViewModel.onOtpChange(it) }
                )

                Spacer(modifier = Modifier.height(100.dp))

                AppCustomButton(
                    modifier = Modifier.fillMaxWidth(),
                    firstButtonText = "Submit OTP",
                    onFirstButtonClick = { loginViewModel.onOtpSubmit() }
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = if (uiState.renewSeconds == 0) "Resend OTP" else "Resend OTP in 00:${uiState.renewSeconds.toString().padStart(2, '0')}",
                    color = if (uiState.renewSeconds == 0) PrimaryColor else Silver,
                    style = TextStyles.InterBoldS,
                    modifier = Modifier.align(Alignment.CenterHorizontally).clickable(enabled = uiState.renewSeconds == 0) {
                        loginViewModel.onResendOtp()
                    }
                )
            }
        }
    }
}
