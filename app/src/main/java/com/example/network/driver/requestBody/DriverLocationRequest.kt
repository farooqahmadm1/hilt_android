package com.ibex.fleetmanager.common.network.driver.requestBody


import com.google.gson.annotations.SerializedName

data class DriverLocationRequest(
    @SerializedName("creationTime") val time : String,
    @SerializedName("tripExecutionId") val tripExecutionId: String, // 3fa85f64-5717-4562-b3fc-2c963f66afa6
    @SerializedName("lat") val lat: Double, // 0
    @SerializedName("long") val long: Double // 0
)