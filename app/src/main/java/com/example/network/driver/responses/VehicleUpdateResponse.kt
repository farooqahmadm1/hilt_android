package com.ibex.fleetmanager.common.network.driver.responses

import com.google.gson.annotations.SerializedName
import com.ibex.fleetmanager.common.base.ErrorResponse

data class VehicleUpdateResponse(
    @SerializedName("result") val result: VehicleUpdate?,
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

data class VehicleUpdate(
    @SerializedName("success") val success: Boolean, // false
    @SerializedName("message") val message: String // Error
)