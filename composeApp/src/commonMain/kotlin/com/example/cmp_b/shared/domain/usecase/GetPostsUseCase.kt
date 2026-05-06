package com.example.cmp_b.shared.domain.usecase

import com.example.cmp_b.core.utils.NetworkResult
import com.example.cmp_b.shared.domain.model.Post
import com.example.cmp_b.shared.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow

class GetPostsUseCase(
    private val repository: PostRepository
) {
    suspend operator fun invoke(): Flow<NetworkResult<List<Post>>> {
        return repository.getPosts()
    }
}
