package com.ibex.fleetmanager.common.network.driver.responses


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.ibex.fleetmanager.common.base.ErrorResponse

data class PastTripResponse(
    @SerializedName("result") val result: PastResult?,
    @SerializedName("targetUrl") val targetUrl: String?, // null
    @SerializedName("success") val _success: Boolean?, // true
    @SerializedName("error") val error: ErrorResponse?, // null
    @SerializedName("unAuthorizedRequest") val unAuthorizedRequest: Boolean, // false
    @SerializedName("__abp") val abp: Boolean? // true
) {
    val success
        get() = _success
            ?: throw IllegalArgumentException("Success value is required.Found Null!")
}

data class PastResult(
    @SerializedName("totalCount") val totalCount: Int?, // 20
    @SerializedName("items") val items: List<PastTrip>?
)

@Entity(tableName = "PastTrip")
data class PastTrip(
    @PrimaryKey
    @SerializedName("id") val id: String, // e2d279b9-4ac7-4797-a56d-08d7f0e9bf2d
    @SerializedName("creationTime") val creationTime: String? = "", // 2020-05-05T17:22:20.4673318+05:00
    @SerializedName("trip") val trip: PastTripData?,
    @SerializedName("startTime") val startTime: String? = "", // 2020-05-05T17:22:24+05:00
    @SerializedName("endTime") val endTime: String? = "", // 2020-05-06T14:25:23+05:00
    @SerializedName("status") val status: Int, // 2
    @SerializedName("directionPolyline") val directionPolyline: String?, // 2
    @SerializedName("distanceTravelled") val distanceTravelled: Int = 0, // 4245
    @SerializedName("tripExecutionDrivers") val tripExecutionDrivers: List<PastTripExecutionDriver>?
)

data class PastTripData(
    @SerializedName("tripId") val tripId: Int? = 0, // 32
    @SerializedName("tripStartAddress") val tripStartAddress: String? = "", // UMT
    @SerializedName("tripEndAddress") val tripEndAddress: String? = "", // IBEX
    @SerializedName("creationTime") val creationTime: String? = "", // 2020-05-05T14:02:16.4452846+05:00
    @SerializedName("name") val name: String? = "", // HumzaTestTrip1
    @SerializedName("startTime") val startTime: String? = "", // 2020-05-05T17:40:00+05:00
    @SerializedName("route") val route: PastRoute?,
    @SerializedName("routeId") val routeId: Int?, // 12
    @SerializedName("driverId") val driverId: Int?, // 11
    @SerializedName("type") val type: Int? // 1
)

data class PastRoute(
    @SerializedName("name") val name: String? = "", // NewTrip
    @SerializedName("startingPointLat") val startingPointLat: Double?, // 31.4675355
    @SerializedName("startingPointLong") val startingPointLong: Double?, // 74.3071702
    @SerializedName("endingPointLat") val endingPointLat: Double?, // 31.4590928
    @SerializedName("endingPointLong") val endingPointLong: Double?, // 74.2465144
    @SerializedName("startingAddress") val startingAddress: String? = "", // Akbar Chowk Township
    @SerializedName("endingAddress") val endingAddress: String? = "", // IBEX Global - The Resource Group Company
    @SerializedName("usersAssociated") val usersAssociated: Int?, // 0
    @SerializedName("id") val id: Int? // 12
)

data class PastTripExecutionDriver(
    @SerializedName("passenger") val passenger: PastPassenger?, // null
    @SerializedName("dropTime") val dropTime: String? = "", // 0001-01-01T00:00:00+05:00
    @SerializedName("status") val status: Int?
)

data class PastPassenger(
    @SerializedName("userName") val userName: String? = "", // string
    @SerializedName("fullName") val fullName: String? = "", // string
    @SerializedName("phoneNumber") val phoneNumber: String? = "", // string
    @SerializedName("employeeId") val employeeId: String? = "", // string
    @SerializedName("pickupLat") val pickupLat: Double?, // 0
    @SerializedName("pickupLong") val pickupLong: Double?, // 0
    @SerializedName("pickupAddress") val pickupAddress: String? = "", // string
    @SerializedName("dropLat") val dropLat: Double?, // 0
    @SerializedName("dropLong") val dropLong: Double?, // 0
    @SerializedName("dropAddress") val dropAddress: String? = "", // string
    @SerializedName("profilePicture") val profilePicture: String? = "" // string
) {
    constructor() : this(
        "", "",
        "", "",
        0.0, 0.0, "",
        0.0, 0.0,
        "", ""
    )
}