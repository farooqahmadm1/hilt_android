package com.ibex.fleetmanager.common.network.user.responses

import com.google.gson.annotations.SerializedName
import com.ibex.fleetmanager.common.base.ErrorResponse

data class ResetForgotPasswordResponse(
    @SerializedName("result") val result: ResetForgotPasswordResult?,
    @SerializedName("targetUrl") val targetUrl: String,
    @SerializedName("success") val _success: Boolean?,
    @SerializedName("error") val error: ErrorResponse?,
    @SerializedName("unAuthorizedRequest") val unAuthorizedRequest: Boolean,
    @SerializedName("__abp") val __abp: Boolean?
) {
    val success
        get() = _success
            ?: throw IllegalArgumentException("Success value is required. Found Null!")
}

data class ResetForgotPasswordResult(
    @SerializedName("sendStatus") val status: Boolean,
    @SerializedName("message") val message: String
)