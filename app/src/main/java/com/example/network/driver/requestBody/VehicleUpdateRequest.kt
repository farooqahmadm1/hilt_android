package com.ibex.fleetmanager.common.network.driver.requestBody


import com.google.gson.annotations.SerializedName

data class VehicleUpdateRequest(
    @SerializedName("make") val make: String, // string
    @SerializedName("model") val model: String, // string
    @SerializedName("registrationNumber") val registrationNumber: String, // string
    @SerializedName("capacity") val capacity: Int, // 0
    @SerializedName("color") val color: String, // string
    @SerializedName("driverId") val driverId: Int, // 0
    @SerializedName("validity") val validity: Int // 1
)