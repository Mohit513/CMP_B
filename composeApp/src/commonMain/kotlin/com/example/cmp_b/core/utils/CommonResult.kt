package com.example.cmp_b.core.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

sealed class CommonResult<out T> {
    object Loading : CommonResult<Nothing>()

    // Success: business success (status = 1 inside 200 OK)
    data class Success<T>(val data: T?) : CommonResult<T>()

    // Business-level error (status = 0 inside 200 OK)
    data class ApiError(val status: Int, val message: String) : CommonResult<Nothing>()

    // Transport-level error (non-200 HTTP code: 400, 401, 500, etc.)
    data class HttpError(val code: Int, val message: String) : CommonResult<Nothing>()

    // Unexpected exceptions (parsing, crashes, etc.)
    data class Failure(val msg: Throwable?) : CommonResult<Nothing>()

    // No connectivity
    object NoInternet : CommonResult<Nothing>()
    object Retry : CommonResult<Nothing>() // when refresh succeeds, request should retry
    object SessionExpired : CommonResult<Nothing>() // when refresh fails or 401 unrecoverable
}

/**
 * Utility: Map Success<T> → Success<R>
 */
fun <T, R> CommonResult<T>.map(transform: (T?) -> R): CommonResult<R> {
    return when (this) {
        is CommonResult.Success -> CommonResult.Success(transform(data))
        is CommonResult.Failure -> CommonResult.Failure(msg)
        is CommonResult.ApiError -> CommonResult.ApiError(status, message)
        is CommonResult.HttpError -> CommonResult.HttpError(code, message)
        is CommonResult.NoInternet -> CommonResult.NoInternet
        is CommonResult.Loading -> CommonResult.Loading
        is CommonResult.Retry -> CommonResult.Retry
        is CommonResult.SessionExpired -> CommonResult.SessionExpired
    }
}

/**
 * Do something on success without changing the flow
 */
fun <T> Flow<CommonResult<T>>.doOnSuccess(action: suspend (T?) -> Unit): Flow<CommonResult<T>> =
    transform { result ->
        if (result is CommonResult.Success) action(result.data)
        emit(result)
    }

/**
 * Do something on failure without changing the flow
 */
fun <T> Flow<CommonResult<T>>.doOnFailure(action: suspend (Throwable?) -> Unit): Flow<CommonResult<T>> =
    transform { result ->
        if (result is CommonResult.Failure) action(result.msg)
        emit(result)
    }

/**
 * Do something on loading without changing the flow
 */
fun <T> Flow<CommonResult<T>>.doOnLoading(action: suspend () -> Unit): Flow<CommonResult<T>> =
    transform { result ->
        if (result is CommonResult.Loading) action()
        emit(result)
    }
