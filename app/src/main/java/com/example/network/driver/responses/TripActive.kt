package com.ibex.fleetmanager.common.network.driver.responses


import com.google.gson.annotations.SerializedName
import com.ibex.fleetmanager.common.base.ErrorResponse


data class TripActiveResponse(
    @SerializedName("result") val result: TripActive?,
    @SerializedName("targetUrl") val targetUrl: String?, // null
    @SerializedName("success") val _success: Boolean?, // true
    @SerializedName("error") val error: ErrorResponse?, // null
    @SerializedName("unAuthorizedRequest") val unAuthorizedRequest: Boolean, // false
    @SerializedName("__abp") val abp: Boolean? // true
) {
    val success
        get() = _success
            ?: throw IllegalArgumentException("Success value is required.Found Null!")
}

data class TripActive(
    @SerializedName("tripExecutionId") val tripExecutionId: String, // 3fa85f64-5717-4562-b3fc-2c963f66afa6
    @SerializedName("status") val status: Int // 1
)