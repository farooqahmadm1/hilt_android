package com.ibex.fleetmanager.common.network.user.requestBody

import com.google.gson.annotations.SerializedName

data class UserRequestCommenytRequestBody(
    @SerializedName("requestId") val requestId: String,
    @SerializedName("message") val message: String
)