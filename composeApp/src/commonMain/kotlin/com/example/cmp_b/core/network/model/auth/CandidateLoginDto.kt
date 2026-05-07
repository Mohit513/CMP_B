package com.example.cmp_b.core.network.model.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CandidateLoginRequestDto(
    @SerialName("APKVersion")
    val apkVersion: String = "",
    @SerialName("AndroidVersion")
    val androidVersion: String = "",
    @SerialName("BuildNo")
    val buildNo: String = "",
    @SerialName("EmployeeCode")
    val employeeCode: String,
    @SerialName("Mobile")
    val mobile: String = "",
    @SerialName("ModelNo")
    val modelNo: String = "",
    @SerialName("SignupSource")
    val signupSource: String = ""
)

@Serializable
data class CandidateLoginResponseDto(
    @SerialName("CandidateStatus")
    val candidateStatus: String? = null,
    @SerialName("InnovID")
    val innovID: String? = null,
    @SerialName("IsDummy")
    val isDummy: Boolean? = false,
    @SerialName("IsMigrated")
    val isMigrated: String? = null,
    @SerialName("IsTransferred")
    val isTransferred: String? = null,
    @SerialName("Mobile")
    val mobile: String? = null,
    @SerialName("OTP")
    val otp: String? = null,
    @SerialName("Status")
    val status: String? = null
)

@Serializable
data class LoginOtpValidateRequestDto(
    @SerialName("Mobile")
    val mobile: String = "",
    @SerialName("OTP")
    val otp: String = ""
)

@Serializable
data class LoginOtpValidateResponseDto(
    @SerialName("InnovID")
    val innovID: String? = "",
    @SerialName("OTPStatus")
    val otpStatus: String? = "",
    @SerialName("TokenID")
    val tokenID: String? = ""
)
