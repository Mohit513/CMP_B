package com.example.cmp_b.feature.home.domain.usecase

import com.example.cmp_b.core.utils.NetworkResult
import com.example.cmp_b.core.utils.map
import com.example.cmp_b.feature.home.domain.model.HomePost
import com.example.cmp_b.feature.home.domain.mapper.HomePostMapper
import com.example.cmp_b.shared.domain.usecase.GetPostsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetHomePostsUseCase(
    private val getPostsUseCase: GetPostsUseCase,
    private val homePostMapper: HomePostMapper
) {
    suspend operator fun invoke(): Flow<NetworkResult<List<HomePost>>> {
        return getPostsUseCase().map { result ->
            result.map { posts ->
                homePostMapper.mapFromList(posts)
            }
        }
    }
}
