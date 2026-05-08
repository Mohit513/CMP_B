package com.example.cmp_b.shared.domain.repository

import com.example.cmp_b.core.data.network.auth.CandidateLoginRequestDto
import com.example.cmp_b.core.data.network.auth.CandidateLoginResponseDto
import com.example.cmp_b.core.data.network.auth.LoginOtpValidateRequestDto
import com.example.cmp_b.core.data.network.auth.LoginOtpValidateResponseDto
import com.example.cmp_b.core.data.network.api.common.NetworkResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun callCandidateLoginApi(request: CandidateLoginRequestDto): Flow<NetworkResult<CandidateLoginResponseDto>>
    suspend fun callLoginOtpValidateApi(request: LoginOtpValidateRequestDto): Flow<NetworkResult<LoginOtpValidateResponseDto>>
}
