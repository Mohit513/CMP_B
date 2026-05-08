package com.example.cmp_b.shared.domain.usecase

import com.example.cmp_b.core.data.network.auth.CandidateLoginRequestDto
import com.example.cmp_b.core.data.network.api.common.NetworkResult
import com.example.cmp_b.core.data.network.api.common.map
import com.example.cmp_b.shared.data.mapper.CandidateLoginMapper
import com.example.cmp_b.shared.domain.model.auth.CandidateLoginUiModel
import com.example.cmp_b.shared.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CandidateLoginUseCase(
    private val authRepository: AuthRepository,
    private val candidateLoginMapper: CandidateLoginMapper
) {
    suspend fun callCandidateLoginApi(
        request: CandidateLoginRequestDto
    ): Flow<NetworkResult<CandidateLoginUiModel>> {
        return authRepository.callCandidateLoginApi(request)
            .map { result ->
                result.map { response ->
                    candidateLoginMapper.mapFrom(response)
                }
            }
    }
}
