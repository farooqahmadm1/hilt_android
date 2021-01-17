package com.ibex.fleetmanager.common.network.driver.requestBody


import com.google.gson.annotations.SerializedName

data class DriverBoardingRequest(
    @SerializedName("tripExecutionDriverId") val tripExecutionDriverId: String, // 3fa85f64-5717-4562-b3fc-2c963f66afa6
    @SerializedName("pickupTime") val pickupTime: String, // 2020-04-27T05:28:41.720Z
    @SerializedName("dropTime") val dropTime: String? = "", // 2020-04-27T05:28:41.720Z
    @SerializedName("status") val status: Int // 1
)