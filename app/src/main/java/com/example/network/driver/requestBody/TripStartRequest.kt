package com.ibex.fleetmanager.common.network.driver.requestBody


import com.google.gson.annotations.SerializedName

data class TripStartRequest(
    @SerializedName("driverId") val driverId: Int, // 0
    @SerializedName("tripId") val tripId: Int, // 0
    @SerializedName("startTime") val startTime: String, // 2020-04-27T05:24:28.243Z
    @SerializedName("status") val status: Int // 1
)