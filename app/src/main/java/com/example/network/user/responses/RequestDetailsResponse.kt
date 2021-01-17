package com.ibex.fleetmanager.common.network.user.responses

import com.google.gson.annotations.SerializedName
import com.ibex.fleetmanager.common.base.ErrorResponse

data class RequestDetailsResponse(
    @SerializedName("result") val result: RequestDetailsResultObject?,
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

data class RequestDetailsResultObject(
    @SerializedName("category") val category: Int,
    @SerializedName("subCategory") val subCategory: Int,
    @SerializedName("status") val status: Int,
    @SerializedName("requestNumber") val requestNumber: Int,
    @SerializedName("creationTime") val creationTime: String,
    @SerializedName("messages") val messages: List<RequestDetailsMessages>
)

data class RequestDetailsMessages(
    @SerializedName("message") val message: String,
    @SerializedName("userType") val userType: String,
    @SerializedName("isDeleted") val isDeleted: Boolean,
    @SerializedName("deleterUserId") val deleterUserId: String,
    @SerializedName("deletionTime") val deletionTime: String,
    @SerializedName("lastModificationTime") val lastModificationTime: String,
    @SerializedName("lastModifierUserId") val lastModifierUserId: String,
    @SerializedName("creationTime") val creationTime: String,
    @SerializedName("creatorUserId") val creatorUserId: String,
    @SerializedName("id") val id: String
)