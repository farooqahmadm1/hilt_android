package com.ibex.fleetmanager.common.network.user.responses

import com.google.gson.annotations.SerializedName
import com.ibex.fleetmanager.common.base.ErrorResponse
import com.ibex.fleetmanager.common.network.driver.responses.Route

data class ScheduleDetailsResponse(
    @SerializedName("result") val result: ScheduleDetailsObject?,
    @SerializedName("targetUrl") val targetUrl: String,
    @SerializedName("success") val _success: Boolean?,
    @SerializedName("error") val error: ErrorResponse?,
    @SerializedName("unAuthorizedRequest") val unAuthorizedRequest: Boolean,
    @SerializedName("__abp") val __abp: Boolean?
) {
    val success
        get() = _success
            ?: throw IllegalArgumentException("Success value is required.Found Null!")
}

data class ScheduleDetailsObject(
    @SerializedName("scheduleStartDate") val scheduleStartDate: String,
    @SerializedName("scheduleEndDate") val scheduleEndDate: String,
    @SerializedName("serviceType") val serviceType: Int,
    @SerializedName("pickupLat") val pickupLat: Double,
    @SerializedName("pickupLong") val pickupLong: Double,
    @SerializedName("dropLat") val dropLat: Double,
    @SerializedName("dropLong") val dropLong: Double,
    @SerializedName("userName") val userName: String,
    @SerializedName("userId") val userId: Int,
    @SerializedName("user") val user: User,
    @SerializedName("scheduleStatus") val scheduleStatus: Int,
    @SerializedName("tenantId") val tenantId: Int,
    @SerializedName("scheduleSpecifics") val scheduleSpecifics: List<ScheduleSpecificsDetails>,
    @SerializedName("id") val id: Int
)

data class ScheduleSpecificsDetails(

    @SerializedName("weekday") val weekday: String,
    @SerializedName("pickupTime") val pickupTime: String?,
    @SerializedName("pickupTripId") val pickupTripId: Int,
    @SerializedName("dropoffTime") val dropoffTime: String?,
    @SerializedName("dropoffTripId") val dropoffTripId: Int,
    @SerializedName("pickupTrip") val pickupTrip: PickupTrip?,
    @SerializedName("dropoffTrip") val dropoffTrip: DropoffTrip?,
    @SerializedName("id") val id: String
)

data class PickupTrip(
    @SerializedName("creationTime") val creationTime: String,
    @SerializedName("name") val name: String,
    @SerializedName("tripStartAddress") val tripStartAddress: String,
    @SerializedName("tripEndAddress") val tripEndAddress: String,
    @SerializedName("startTime") val startTime: String,
    @SerializedName("route") val route: Route,
    @SerializedName("routeId") val routeId: Int,
    @SerializedName("passengerCount") val passengerCount: Int,
    @SerializedName("driverName") val driverName: String,
    @SerializedName("driverSurname") val driverSurname: String,
    @SerializedName("vehicle") val vehicle: String,
    @SerializedName("driverId") val driverId: Int,
    @SerializedName("isActive") val isActive: Boolean,
    @SerializedName("passengers") val passengers: String,
    @SerializedName("type") val type: Int,
    @SerializedName("id") val id: Int
)

data class DropoffTrip(

    @SerializedName("creationTime") val creationTime: String,
    @SerializedName("name") val name: String,
    @SerializedName("tripStartAddress") val tripStartAddress: String,
    @SerializedName("tripEndAddress") val tripEndAddress: String,
    @SerializedName("startTime") val startTime: String,
    @SerializedName("route") val route: Route,
    @SerializedName("routeId") val routeId: Int,
    @SerializedName("passengerCount") val passengerCount: Int,
    @SerializedName("driverName") val driverName: String,
    @SerializedName("driverSurname") val driverSurname: String,
    @SerializedName("vehicle") val vehicle: String,
    @SerializedName("driverId") val driverId: Int,
    @SerializedName("isActive") val isActive: Boolean,
    @SerializedName("passengers") val passengers: String,
    @SerializedName("type") val type: Int,
    @SerializedName("id") val id: Int
)