package com.example.cmp_b.shared.domain.usecase

import com.example.cmp_b.core.data.network.auth.LoginOtpValidateRequestDto
import com.example.cmp_b.core.data.network.api.common.NetworkResult
import com.example.cmp_b.core.data.network.api.common.map
import com.example.cmp_b.shared.data.mapper.LoginOtpValidateMapper
import com.example.cmp_b.shared.domain.model.auth.LoginOtpValidateUiModel
import com.example.cmp_b.shared.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LoginOtpValidateUseCase(
    private val authRepository: AuthRepository,
    private val otpValidateMapper: LoginOtpValidateMapper
) {
    suspend fun callLoginOtpValidateApi(
        request: LoginOtpValidateRequestDto
    ): Flow<NetworkResult<LoginOtpValidateUiModel>> {
        return authRepository.callLoginOtpValidateApi(request)
            .map { result ->
                result.map { response ->
                    otpValidateMapper.mapFrom(response)
                }
            }
    }
}
