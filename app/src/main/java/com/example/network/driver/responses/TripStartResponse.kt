package com.ibex.fleetmanager.common.network.driver.responses


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.ibex.fleetmanager.common.base.ErrorResponse


data class TripStartResponse(
    @SerializedName("result") val result: TripStart?,
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


@Entity(tableName = "TripStart")
data class TripStart(
    @PrimaryKey
    @SerializedName("id") val id: String, // 3fa85f64-5717-4562-b3fc-2c963f66afa6
    @SerializedName("trip") val trip: Trip,
    @SerializedName("creationTime") val creationTime: String?,
    @SerializedName("startTime") val startTime: String?, // 2020-04-27T05:36:54.305Z
    @SerializedName("endTime") val endTime: String?, // 2020-04-27T05:36:54.305Z
    @SerializedName("status") val status: Int?, // 1
    @SerializedName("tripExecutionDrivers") val tripExecutionDrivers: List<TripExecutionDriver>?
) {
    val startTimeNOtNull
        get() = startTime
            ?: throw IllegalArgumentException("Start Time for trip data is required.Found Null!")
}

data class TripExecutionDriver(
    @SerializedName("passenger") val _passenger: Passenger?,
    @SerializedName("tripExecutionId") val tripExecutionId: String, // 3fa85f64-5717-4562-b3fc-2c963f66afa6
    @SerializedName("passengerId") val passengerId: Int, // 0
    @SerializedName("pickupTime") val pickupTime: String, // 2020-04-27T05:36:54.305Z
    @SerializedName("dropTime") val dropTime: String, // 2020-04-27T05:36:54.305Z
    @SerializedName("driverArrived") val driverArrived: Boolean,
    @SerializedName("status") val status: Int, // 1
    @SerializedName("id") val id: String // 3fa85f64-5717-4562-b3fc-2c963f66afa6
) {
    val passenger
        get() = _passenger
            ?: throw IllegalArgumentException("Passenger data is required.Found Null!")
}

data class Passenger(
    @SerializedName("fullName") val fullName: String, // string
    @SerializedName("phoneNumber") val phoneNumber: String, // string
    @SerializedName("employeeId") val employeeId: String, // string
    @SerializedName("pickupLat") val pickupLat: Double, // 0
    @SerializedName("pickupLong") val pickupLong: Double, // 0
    @SerializedName("pickupAddress") val pickupAddress: String, // string
    @SerializedName("dropLat") val dropLat: Double, // 0
    @SerializedName("dropLong") val dropLong: Double, // 0
    @SerializedName("dropAddress") val dropAddress: String, // string
    @SerializedName("profilePicture") val profilePicture: String // string
)

data class Trip(
    @SerializedName("tripId") val id: Int, // 0
    @SerializedName("tripStartAddress") val startAddress: String,
    @SerializedName("tripEndAddress") val endAddress: String,
    @SerializedName("creationTime") val creationTime: String, // 2020-04-27T05:36:54.305Z
    @SerializedName("name") val name: String, // string
    @SerializedName("startTime") val startTime: String, // 2020-04-27T05:36:54.305Z
    @SerializedName("route") val _route: Route?,
    @SerializedName("routeId") val routeId: Int, // 0
    @SerializedName("driverId") val driverId: Int, // 0
    @SerializedName("type") val type: Int
) {
    val route
        get() = _route
            ?: throw IllegalArgumentException("Route data is required.Found Null!")
}

data class Route(
    @SerializedName("name") val name: String, // string
    @SerializedName("startingPointLat") val startingPointLat: Double, // 0
    @SerializedName("startingPointLong") val startingPointLong: Double, // 0
    @SerializedName("endingPointLat") val endingPointLat: Double, // 0
    @SerializedName("endingPointLong") val endingPointLong: Double, // 0
    @SerializedName("isActive") val isActive: Boolean, // true
    @SerializedName("startingAddress") val startingAddress: String, // string
    @SerializedName("endingAddress") val endingAddress: String, // string
    @SerializedName("id") val id: Int // 0
)