package com.ibex.fleetmanager.common.base

import com.ibex.fleetmanager.common.custom.errors.ErrorHandler


sealed class Result<out T : Any>

class Success<out T : Any>(val data: T) : Result<T>()
class Failure<out T : Any>(val data: T) : Result<T>()

class Error(
    val exception: Throwable,
    val message: String = exception.message ?: ErrorHandler.UNKNOWN_ERROR
) : Result<Nothing>()

class Progress(val isLoading: Boolean) : Result<Nothing>()