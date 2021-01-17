package com.ibex.fleetmanager.common.network.user.responses

import com.google.gson.annotations.SerializedName
import com.ibex.fleetmanager.common.base.ErrorResponse

data class RegisterResponse(
    @SerializedName("result") val result: RegisterResult?,
    @SerializedName("targetUrl") val targetUrl: String,
    @SerializedName("success") val _success: Boolean?,
    @SerializedName("error") val error: ErrorResponse?,
    @SerializedName("unAuthorizedRequest") val unAuthorizedRequest: Boolean,
    @SerializedName("__abp") val __abp: Boolean?
) {
    val success
        get() = _success
            ?: throw IllegalArgumentException("Success value is required.Found Null!")
}

data class RegisterResult(
    @SerializedName("canLogin") val _canLogin: Boolean?,
    @SerializedName("message") val message: String,
    @SerializedName("isActive") val active:Boolean
) {
    val canLogin
        get() = _canLogin
            ?: throw java.lang.IllegalArgumentException("canLogin Value is Required.Found Null!")
}

