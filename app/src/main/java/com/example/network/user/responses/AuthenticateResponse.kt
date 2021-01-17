package com.ibex.fleetmanager.common.network.user.responses

import com.google.gson.annotations.SerializedName
import com.ibex.fleetmanager.common.base.ErrorResponse

data class AuthenticateResponse(
    @SerializedName("result") val result: AuthenticateResult?,
    @SerializedName("targetUrl") val targetUrl: String? = "",
    @SerializedName("success") val _success: Boolean?,
    @SerializedName("error") val error: ErrorResponse?,
    @SerializedName("unAuthorizedRequest") val unAuthorizedRequest: Boolean,
    @SerializedName("__abp") val __abp: Boolean?
) {
    val success
        get() = _success
            ?: throw IllegalArgumentException("Success value is required.Found Null!")
}

data class AuthenticateResult(
    @SerializedName("accessToken") val _accessToken: String?,
    @SerializedName("encryptedAccessToken") val encryptedAccessToken: String? = "",
    @SerializedName("expireInSeconds") val expireInSeconds: Int? = 0,
    @SerializedName("userId") val _userId: Int?,
    @SerializedName("role") val userType: Int
) {
    val accessToken
        get() = _accessToken
            ?: throw IllegalArgumentException("Access Token is required.Found Null!")
    val userId
        get() = _userId ?: throw IllegalArgumentException("User Id is required.Found Null!")
}
