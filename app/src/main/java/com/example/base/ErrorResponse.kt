package com.ibex.fleetmanager.common.base

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("details") val details: String,
    @SerializedName("validationErrors") val validationErrors: String? = ""
)