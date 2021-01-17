package com.ibex.fleetmanager.common.network.user.responses

import com.google.gson.annotations.SerializedName
import com.ibex.fleetmanager.common.base.ErrorResponse
import com.ibex.fleetmanager.common.network.responses.RoutesItems
import com.ibex.fleetmanager.common.network.responses.Vehicle

data class PastScheduleDetailsResponse(
    @SerializedName("result") val result: PastScheduleDetailsResultObject?,
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

data class PastScheduleDetailsResultObject(

    @SerializedName("tripExecution") val tripExecution: TripExecution,
    @SerializedName("tripExecutionId") val tripExecutionId: String,
    @SerializedName("passenger") val passenger: Passenger,
    @SerializedName("passengerId") val passengerId: Int,
    @SerializedName("schedule") val schedule: Schedule,
    @SerializedName("scheduleId") val scheduleId: String,
    @SerializedName("tripId") val tripId: Int,
    @SerializedName("trip") val trip: PastTripDetails,
    @SerializedName("date") val date: String,
    @SerializedName("pickupTime") val pickupTime: String,
    @SerializedName("dropTime") val dropTime: String,
    @SerializedName("type") val type: String,
    @SerializedName("pickupLat") val pickupLat: Int,
    @SerializedName("pickupLong") val pickupLong: Int,
    @SerializedName("dropLat") val dropLat: Int,
    @SerializedName("dropLong") val dropLong: Int,
    @SerializedName("status") val status: Int,
    @SerializedName("tenantId") val tenantId: Int
)

data class Schedule(

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
    @SerializedName("scheduleSpecifics") val scheduleSpecifics: List<String>,
    @SerializedName("id") val id: Int
)

data class User(

    @SerializedName("userName") val userName: String,
    @SerializedName("name") val name: String,
    @SerializedName("surname") val surname: String,
    @SerializedName("emailAddress") val emailAddress: String,
    @SerializedName("isActive") val isActive: Boolean,
    @SerializedName("fullName") val fullName: String,
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("lastLoginTime") val lastLoginTime: String,
    @SerializedName("creationTime") val creationTime: String,
    @SerializedName("roleNames") val roleNames: String,
    @SerializedName("employeeId") val employeeId: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("city") val city: String,
    @SerializedName("pickupLat") val pickupLat: Double,
    @SerializedName("pickupLong") val pickupLong: Double,
    @SerializedName("pickupAddress") val pickupAddress: String,
    @SerializedName("dropLat") val dropLat: Double,
    @SerializedName("dropLong") val dropLong: Double,
    @SerializedName("dropAddress") val dropAddress: String,
    @SerializedName("department") val department: String,
    @SerializedName("entity") val entity: String,
    @SerializedName("profilePicture") val profilePicture: String,
    @SerializedName("cnic") val cnic: String,
    @SerializedName("cnicNumber") val cnicNumber: String,
    @SerializedName("license") val license: String,
    @SerializedName("licenseNumber") val licenseNumber: String,
    @SerializedName("route") val route: Routes,
    @SerializedName("routeId") val routeId: Int,
    @SerializedName("id") val id: Int
)

data class PastTripDetails(

    @SerializedName("creationTime") val creationTime: String,
    @SerializedName("name") val name: String,
    @SerializedName("tripStartAddress") val tripStartAddress: String,
    @SerializedName("tripEndAddress") val tripEndAddress: String,
    @SerializedName("startTime") val startTime: String,
    @SerializedName("route") val route: RoutesItems,
    @SerializedName("routeId") val routeId: Int,
    @SerializedName("passengerCount") val passengerCount: Int,
    @SerializedName("driverName") val driverName: String,
    @SerializedName("driverSurname") val driverSurname: String,
    @SerializedName("vehicle") val vehicle: Vehicle,
    @SerializedName("driverId") val driverId: Int,
    @SerializedName("isActive") val isActive: Boolean,
    @SerializedName("passengers") val passengers: String,
    @SerializedName("type") val type: Int,
    @SerializedName("id") val id: Int
)

data class TripExecution(
    @SerializedName("creationTime") val creationTime: String,
    @SerializedName("trip") val trip: String,
    @SerializedName("driver") val driver: DriverDetails,
    @SerializedName("startTime") val startTime: String,
    @SerializedName("endTime") val endTime: String,
    @SerializedName("status") val status: Int,
    @SerializedName("driverName") val driverName: String,
    @SerializedName("tripExecutionDrivers") val tripExecutionDrivers: List<String>,
    @SerializedName("id") val id: String
)

