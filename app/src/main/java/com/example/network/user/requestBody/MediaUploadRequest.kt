package com.ibex.fleetmanager.common.network.user.requestBody

import com.google.gson.annotations.SerializedName
import java.io.File

data class MediaUploadRequest(
    val userId: String = "",
    @SerializedName("VehicleId") val vehicleId: String = "",
    val driverId: String = "",
    val mediaType: String = "",
    val file: File
)