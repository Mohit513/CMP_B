package com.example.cmp_b.data.model

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val data: T? = null,
    val message: String? = null,
    val status: Int? = null
)
