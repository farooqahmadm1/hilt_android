package com.ibex.fleetmanager.common.network.driver.responses


import com.google.gson.annotations.SerializedName
import com.ibex.fleetmanager.common.base.ErrorResponse


data class NotificationResponse(
    @SerializedName("result") val result: Notification?,
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

data class Notification(
    @SerializedName("items") val list: List<NotificationItem>?,
    @SerializedName("totalCount") val totalCount: Int
)

data class NotificationItem(
    @SerializedName("userId") val userId: Int, // 0
    @SerializedName("message") val message: String, // string
    @SerializedName("username") val name: String,
    @SerializedName("userType") val type: Int,
    @SerializedName("tripId") val tripId: Int?,
    @SerializedName("type") val notificationType: Int,
    @SerializedName("creationTime") val creationTime: String, // 2020-05-20T03:50:05.120Z
    @SerializedName("creatorUserId") val creatorUserId: Int, // 0
    @SerializedName("id") val id: String // 3fa85f64-5717-4562-b3fc-2c963f66afa6
)