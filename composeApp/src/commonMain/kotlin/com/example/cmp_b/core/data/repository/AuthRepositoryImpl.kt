package com.example.cmp_b.core.data.repository

import com.example.cmp_b.core.data.network.remote.ApiService
import com.example.cmp_b.core.data.network.auth.CandidateLoginRequestDto
import com.example.cmp_b.core.data.network.auth.CandidateLoginResponseDto
import com.example.cmp_b.core.data.network.auth.LoginOtpValidateRequestDto
import com.example.cmp_b.core.data.network.auth.LoginOtpValidateResponseDto
import com.example.cmp_b.core.data.network.api.common.NetworkResult
import com.example.cmp_b.shared.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepositoryImpl(
    private val apiService: ApiService
) : BaseRepository(), AuthRepository {

    override suspend fun callCandidateLoginApi(
        request: CandidateLoginRequestDto
    ): Flow<NetworkResult<CandidateLoginResponseDto>> = flow {
        emit(NetworkResult.Loading)
        val result = safeApiCall<CandidateLoginResponseDto> {
            apiService.callCandidateLoginApi(request)
        }
        emit(result)
    }

    override suspend fun callLoginOtpValidateApi(
        request: LoginOtpValidateRequestDto
    ): Flow<NetworkResult<LoginOtpValidateResponseDto>> = flow {
        emit(NetworkResult.Loading)
        val result = safeApiCall<LoginOtpValidateResponseDto> {
            apiService.callLoginOtpValidateApi(request)
        }
        emit(result)
    }
}