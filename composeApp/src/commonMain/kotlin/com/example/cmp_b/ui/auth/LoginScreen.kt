package com.example.cmp_b.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.example.cmp_b.navigation.AppState
import com.example.cmp_b.navigation.NavigationEvent
import com.example.cmp_b.ui.components.*
import cmp_b.composeapp.generated.resources.Res
import cmp_b.composeapp.generated.resources.image_top_login
import com.example.cmp_b.ui.theme.BackgroundLight
import com.example.cmp_b.ui.theme.Bright_red
import com.example.cmp_b.ui.theme.Silver
import com.example.cmp_b.ui.theme.TextStyles

@Composable
fun LoginScreen(
    appState: AppState,
    loginViewModel: LoginViewModel
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val uiState by loginViewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()
    val focusRequester = remember { FocusRequester() }

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

    // Focus mobile field when returning from OTP
    LaunchedEffect(uiState.isOtpSent) {
        if (!uiState.isOtpSent) {
            focusRequester.requestFocus()
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
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
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit Mobile",
                            tint = BackgroundLight,
                            modifier = Modifier
                                .size(16.dp)
                                .clickable {
                                    loginViewModel.goBackToLogin()
                                    loginViewModel.onMobileChanged(uiState.mobile)
                                }
                        )
                    }
                }
            }
        )

        // LOGIN CONTENT
        if (!uiState.isOtpSent) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 34.dp, horizontal = 16.dp)
            ) {
                if (uiState.isLoading) {
                    Box(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                AppTextFieldWithLabel(
                    modifier = Modifier.focusRequester(focusRequester),
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
                    secondButtonText = "OnBoarding",
                    onFirstButtonClick = { loginViewModel.onLoginClick() },
                    onSecondButtonClick = { loginViewModel.onOnBoardingClick() }
                )
            }

        } else {

            // OTP CONTENT
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 34.dp, horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = if (uiState.renewSeconds == 0)
                        "Resend OTP"
                    else
                        "Resend OTP in ${uiState.renewSeconds}s",

                    color = if (uiState.renewSeconds == 0)
                        Bright_red
                    else
                        Silver,

                    style = TextStyles.InterBoldS,

                    modifier = Modifier.clickable(
                        enabled = uiState.renewSeconds == 0
                    ) {
                        loginViewModel.onResendOtp()
                    }
                )
            }
        }
    }
}
