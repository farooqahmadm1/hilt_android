package com.ibex.fleetmanager.common.network.user.responses


import com.google.gson.annotations.SerializedName
import com.ibex.fleetmanager.common.base.ErrorResponse

data class DepartmentResponse(
    @SerializedName("result") val result: List<DepartmentResult>?,
    @SerializedName("targetUrl") val targetUrl: String,
    @SerializedName("success") val success: Boolean,
    @SerializedName("error") val error: ErrorResponse?,
    @SerializedName("unAuthorizedRequest") val unAuthorizedRequest: Boolean,
    @SerializedName("__abp") val __abp: Boolean?
)

data class DepartmentResult(
    @SerializedName("name") val name: String, // IBEX
    @SerializedName("id") val id: Int // 1
)