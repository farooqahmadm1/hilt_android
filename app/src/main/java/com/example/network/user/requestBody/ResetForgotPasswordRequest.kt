package com.ibex.fleetmanager.common.network.user.requestBody


import com.google.gson.annotations.SerializedName

data class ResetForgotPasswordRequest(
    @SerializedName("emailAddress") val emailAddress: String, // user@example.com
    @SerializedName("passwordResetCode") val passwordResetCode: String, // 12e313
    @SerializedName("password") val password: String // 211313
)