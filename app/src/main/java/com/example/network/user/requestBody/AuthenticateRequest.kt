package com.ibex.fleetmanager.common.network.user.requestBody

import com.google.gson.annotations.SerializedName

data class AuthenticateRequest(
    @SerializedName("userNameOrEmailAddress") val userNameOrEmailAddress: String,
    @SerializedName("password") val password: String,
    @SerializedName("rememberClient") val rememberClient: Boolean
)