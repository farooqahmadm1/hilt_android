package com.example.hilt_android.network

sealed class ResponseResult<out H> {
    data class Success<out T>(val data: T)
        : ResponseResult<T>()
    data class Failure(val error: Throwable)
        : ResponseResult<Nothing>()
    object Pending : ResponseResult<Nothing>()
    object Complete : ResponseResult<Nothing>()
}