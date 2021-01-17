package com.ibex.fleetmanager.common.extensions

import android.accounts.NetworkErrorException
import android.util.Log
import com.ibex.fleetmanager.common.base.Progress
import com.ibex.fleetmanager.common.base.Result
import com.ibex.fleetmanager.common.utility.Utils
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.retryWhen
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import timber.log.Timber
import java.io.IOException
import java.net.SocketTimeoutException

fun Job?.cancelIfActive() {
    if (this?.isActive == true) {
        cancel()
    }
}

/**
 * You may want to apply some common side-effects to your flow to avoid repeating commonly used
 * logic across your app.
 *
 * For e.g. If you want to show/hide progress then use side-effect methods like
 * onStart & onCompletion
 *
 * You can also write common business logic which is applicable to all flows in your application,
 * in this case we are retrying requests 3 times with an exponential delay; if the exception thrown
 * is of type IOException.
 *
 */
fun <T : Any> Flow<Result<T>>.applyCommonSideEffects() =
    retryWhen { cause, attempt ->
        when {
            (cause is SocketTimeoutException  && attempt < Utils.MAX_RETRIES) -> {
                delay(Utils.getBackoffDelay(attempt))
                Timber.d("DriverException : SocketTimeoutException")
                true
            }
            (cause is IOException && attempt < Utils.MAX_RETRIES) -> {
                delay(Utils.getBackoffDelay(attempt))
                Timber.d("DriverException: IO Exception")
                true
            }
            (cause is NetworkErrorException && attempt > Utils.MAX_RETRIES)->{
                Timber.d("DriverException: NetworkErrorException")
                delay(Utils.getBackoffDelay(attempt))
                true
            }
            else -> {
                Timber.d("DriverException: Exception")
                emit(Progress(isLoading = false))
                false
            }
        }
    }
        .onStart { emit(Progress(isLoading = true)) }
        .onCompletion { emit(Progress(isLoading = false)) }

fun <T : Any> Flow<Result<T>>.applyCommonSideEffectsForLocation() =
    retryWhen { cause, attempt ->
        when {
            (cause is SocketTimeoutException  && attempt < Utils.LOCATION_MAX_RETRIES) -> {
                delay(Utils.getBackoffDelay(attempt))
                Timber.d("DriverException : SocketTimeoutException")
                true
            }
            (cause is IOException && attempt < Utils.LOCATION_MAX_RETRIES) -> {
                delay(Utils.getBackoffDelay(attempt))
                Timber.d("DriverException: IO Exception")
                true
            }
            else -> {
                Timber.d("DriverException: Exception")
                emit(Progress(isLoading = false))
                false
            }
        }
    }
        .onStart { emit(Progress(isLoading = true)) }
        .onCompletion { emit(Progress(isLoading = false)) }

@PublishedApi
internal inline fun Retrofit.Builder.callFactory(crossinline body: (Request) -> Call) =
    callFactory { request -> body(request) }

@Suppress("NOTHING_TO_INLINE")
inline fun Retrofit.Builder.delegatingCallFactory(delegate: dagger.Lazy<OkHttpClient>): Retrofit.Builder =
    callFactory {
        delegate.get().newCall(it)
    }