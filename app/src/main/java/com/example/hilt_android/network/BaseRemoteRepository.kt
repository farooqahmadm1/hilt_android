package com.example.hilt_android.network

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Created by farooq in 1/6/2020
 */
abstract class BaseRemoteRepository {

    companion object {
        private const val TAG = "BaseRemoteRepository"
        private const val MESSAGE_KEY = "message"
        private const val ERROR_KEY = "error"
    }

    /**
     * Function that executes the given function on Dispatchers.IO context and switch to Dispatchers.Main context when an error occurs
     * @param callFunction is the function that is returning the wanted object. It must be a suspend function. Eg:
     * override suspend fun loginUser(body: LoginUserBody, emitter: RemoteErrorEmitter): LoginUserResponse?  = safeApiCall( { authApi.loginUser(body)} , emitter)
     * @param emitter is the interface that handles the error messages. The error messages must be displayed on the MainThread, or else they would throw an Exception.
     */
    suspend inline fun <T> safeApiCall(crossinline body: suspend () -> T): ResponseResult<T>? {
        return try {
            val myObject = withContext(Dispatchers.IO) {
                body()
            }
            ResponseResult.Success(myObject)
        } catch (e: Exception) {
            withContext(Dispatchers.IO) {
                e.printStackTrace()
                Log.e("BaseRemoteRepo", "Call error: ${e.localizedMessage}", e.cause)
                when (e) {
                    is HttpException -> {
                        if (e.code() == 401) ResponseResult.Error("", ErrorType.SESSION_EXPIRED)
                        else {
                            val body = e.response()?.errorBody()
                            ResponseResult.Error(getErrorMessage(body), ErrorType.NETWORK)
                        }
                    }
                    is SocketTimeoutException -> ResponseResult.Error("", ErrorType.TIMEOUT)
                    is IOException -> ResponseResult.Error("", ErrorType.NETWORK)
                    else -> ResponseResult.Error(getErrorMessage(null), ErrorType.UNKNOWN)
                }
            }
            null
        }
    }

    fun getErrorMessage(responseBody: ResponseBody?): String {
        return try {
            val jsonObject = JSONObject(responseBody!!.string())
            when {
                jsonObject.has(MESSAGE_KEY) -> jsonObject.getString(MESSAGE_KEY)
                jsonObject.has(ERROR_KEY) -> jsonObject.getString(ERROR_KEY)
                else -> "Something wrong happened"
            }
        } catch (e: Exception) {
            "Something wrong happened"
        }
    }
}