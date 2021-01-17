package com.ibex.fleetmanager.common.network.user.responses

import com.google.gson.annotations.SerializedName

data class DirectionsRouteResponse(
    @SerializedName("startName") val startName: String = "",
    @SerializedName("endName") val endName: String = "",
    @SerializedName("startLat") val startLat: Double?,
    @SerializedName("startLng") val startLng: Double?,
    @SerializedName("endLat") val endLat: Double?,
    @SerializedName("endLng") val endLng: Double?,
    @SerializedName("duration") val duration: String,
    @SerializedName("overviewPolyline") val overviewPolyline: String = ""
)