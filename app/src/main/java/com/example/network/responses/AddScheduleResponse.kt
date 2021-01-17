package com.ibex.fleetmanager.common.network.responses

import com.google.gson.annotations.SerializedName
import com.ibex.fleetmanager.common.base.ErrorResponse

data class AddScheduleResponse(
//    @SerializedName("result") val result: TripsListResult?,
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