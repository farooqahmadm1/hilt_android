package com.ibex.fleetmanager.common.network.common.response

import com.google.gson.annotations.SerializedName
import com.ibex.fleetmanager.common.base.ErrorResponse
import com.ibex.fleetmanager.common.network.driver.responses.NotificationItem

data class ChatNotificationResponse(
    @SerializedName("result") val result: ChatNotificationUpdate?,
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

data class ChatNotificationUpdate(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String
)