package com.ibex.fleetmanager.common.network.driver.requestBody


import com.google.gson.annotations.SerializedName

data class BoardNotificationRequest(
    @SerializedName("userIds") val userIds: List<Int>,
    @SerializedName("message") val message: String
)