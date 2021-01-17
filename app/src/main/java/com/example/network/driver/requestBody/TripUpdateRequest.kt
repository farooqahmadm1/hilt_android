package com.ibex.fleetmanager.common.network.driver.requestBody


import com.google.gson.annotations.SerializedName

data class TripUpdateRequest(
    @SerializedName("endTime") val endTime: String, // 2020-04-27T10:01:57.911Z
    @SerializedName("status") val status: Int, // 1
    @SerializedName("directionPolyline") val directionPolyline: String, // 1
    @SerializedName("distanceTravelled") val distanceTraveled: Long,
    @SerializedName("endingPointLat") val lat: Double,
    @SerializedName("endingPointLong") val long: Double,
    @SerializedName("id") val id: String // 3fa85f64-5717-4562-b3fc-2c963f66afa6
)