package com.ibex.fleetmanager.common.network.user.requestBody

import com.google.gson.annotations.SerializedName

data class PassengerUpdateDestinationCheck(
    @SerializedName("tripExecutionDriverId") val tripExecutionId: String,
    @SerializedName("pickupTime") val pickupTime: String,
    @SerializedName("dropTime") val dropTime: String,
    @SerializedName("status")
    val status: Int
)