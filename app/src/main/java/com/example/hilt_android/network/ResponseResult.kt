package com.example.hilt_android.network

/**
 * Created by farooq in 1/6/2020
 */
sealed class ResponseResult<out H> {
    data class Success<out T>(val data: T) : ResponseResult<T>()
    data class Error(val message: String, val type: ErrorType) : ResponseResult<Nothing>()
    object Pending : ResponseResult<Nothing>()
    object Complete : ResponseResult<Nothing>()
}