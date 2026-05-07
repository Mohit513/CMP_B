package com.example.cmp_b.shared.domain.model.auth

data class CandidateLoginUiModel(
    val candidateStatus: String? = null,
    val innovID: String? = null,
    val isDummy: Boolean? = false,
    val isMigrated: String? = null,
    val isTransferred: String? = null,
    val mobile: String? = null,
    val otp: String? = null,
    val status: String? = null
)

data class LoginOtpValidateUiModel(
    val innovID: String? = "",
    val otpStatus: String? = "",
    val tokenID: String? = ""
)
