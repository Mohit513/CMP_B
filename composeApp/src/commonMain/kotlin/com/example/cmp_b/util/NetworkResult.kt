package com.example.cmp_b.util

sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Error(val message: String, val throwable: Throwable? = null) : NetworkResult<Nothing>()
    object Loading : NetworkResult<Nothing>()
}

fun <T> NetworkResult<T>.onSuccess(action: (T) -> Unit): NetworkResult<T> {
    if (this is NetworkResult.Success) action(data)
    return this
}

fun <T> NetworkResult<T>.onError(action: (String) -> Unit): NetworkResult<T> {
    if (this is NetworkResult.Error) action(message)
    return this
}
