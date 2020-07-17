package com.example.hilt_android.util

import androidx.lifecycle.liveData
import com.example.hilt_android.network.ResponseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

inline fun <T> liveResponse(crossinline body: suspend () ->  ResponseResult<T>) =
    liveData(Dispatchers.IO) {
        emit(ResponseResult.Pending)
        val result = body()
        emit(result)
        emit(ResponseResult.Complete)
    }

suspend inline fun <T> safeApiCall(
    crossinline body: suspend () -> T
): ResponseResult<T> {
    return try {
        // blocking block
        val users = withContext(Dispatchers.IO) {
            body()
        }
        ResponseResult.Success(users)
    } catch (e: Exception) {
        ResponseResult.Failure(e)
    }
}