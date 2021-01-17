package com.ibex.fleetmanager.common.network.user.requestBody

import com.google.gson.annotations.SerializedName

data class CancelScheduledTripRequest(
    @SerializedName("passengerId") val passengerId: Int,
    @SerializedName("tripId") val tripId: Int,
    @SerializedName("scheduleId") val scheduleId: Int,
    @SerializedName("date") val date: String,
    @SerializedName("type") val type: Int,
    @SerializedName("status") val status: Int
)