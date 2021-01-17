package com.ibex.fleetmanager.common.network.driver.responses

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.ibex.fleetmanager.common.network.user.responses.Legs
import com.ibex.fleetmanager.common.network.user.responses.Steps
import com.ibex.fleetmanager.common.network.user.responses.ViaWaypoint

@Entity(tableName = "Trip_Directions")
data class DirectionsDriverRouteResponse(
    @PrimaryKey
    val id: Int = 1,
    @SerializedName("startName") val startName: String = "",
    @SerializedName("endName") val endName: String = "",
    @SerializedName("startLat") val startLat: Double?,
    @SerializedName("startLng") val startLng: Double?,
    @SerializedName("endLat") val endLat: Double?,
    @SerializedName("endLng") val endLng: Double?,
    @SerializedName("waypoint") val waypoint: List<ViaWaypoint>,
    @SerializedName("duration") val duration: Long,
    @SerializedName("steps") val steps: List<Steps>,
    @SerializedName("overviewPolyline") val overviewPolyline: String = "",
    @SerializedName("distance") val distance: Long = 0,
    @SerializedName("wayPointOrder") val waypointOrder: List<Int>,
    @SerializedName("legs") val legs: List<Legs>,
    var startTime: String
)