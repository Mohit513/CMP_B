package com.example.cmp_b.shared.domain.repository

import com.example.cmp_b.core.network.model.auth.CandidateLoginRequestDto
import com.example.cmp_b.core.network.model.auth.CandidateLoginResponseDto
import com.example.cmp_b.core.network.model.auth.LoginOtpValidateRequestDto
import com.example.cmp_b.core.network.model.auth.LoginOtpValidateResponseDto
import com.example.cmp_b.core.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun callCandidateLoginApi(request: CandidateLoginRequestDto): Flow<NetworkResult<CandidateLoginResponseDto>>
    suspend fun callLoginOtpValidateApi(request: LoginOtpValidateRequestDto): Flow<NetworkResult<LoginOtpValidateResponseDto>>
}
