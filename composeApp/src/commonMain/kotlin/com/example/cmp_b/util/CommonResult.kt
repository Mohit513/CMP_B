package com.example.cmp_b.util

sealed class CommonResult<out T> {
    data class Success<out T>(val data: T) : CommonResult<T>()
    data class ApiError(val message: String) : CommonResult<Nothing>()
    data class Failure(val msg: ErrorMessage? = null) : CommonResult<Nothing>()
    data class HttpError(val code: Int) : CommonResult<Nothing>()
    object Loading : CommonResult<Nothing>()
    object NoInternet : CommonResult<Nothing>()
    object Retry : CommonResult<Nothing>()
    object SessionExpired : CommonResult<Nothing>()
}

data class ErrorMessage(val message: String)
