package com.ibex.fleetmanager.common.network.user.responses

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.ibex.fleetmanager.common.base.ErrorResponse

data class OnGoingTripStatusResponse(
    @SerializedName("result") val result: OnGoingTripStatusResult?,
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

data class OnGoingTripStatusResult(
    @SerializedName("tripExecutionId") val tripExecutionId: String,
    @SerializedName("status") val status: Int,
    @SerializedName("passenger") val passenger: Passenger
)

data class Passenger(
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
    @SerializedName("employeeId") val employeeId: Int,
    @SerializedName("gender") val gender: String,
    @SerializedName("pickupLat") val pickupLat: Double,
    @SerializedName("pickupLong") val pickupLong: Double,
    @SerializedName("pickupAddress") val pickupAddress: String,
    @SerializedName("dropLat") val dropLat: Double,
    @SerializedName("dropLong") val dropLong: Double,
    @SerializedName("dropAddress") val dropAddress: String,
    @SerializedName("city") val city: String,
    @SerializedName("department") val department: String,
    @SerializedName("entity") val entity: String,
    @SerializedName("profilePicture") val profilePicture: String,
    @SerializedName("route") val route: String,
    @SerializedName("routeId") val routeId: Int,
    @SerializedName("directionPolyline") val directionPolyline: String?,
    @SerializedName("id") val id: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userName)
        parcel.writeString(name)
        parcel.writeString(surname)
        parcel.writeString(emailAddress)
        parcel.writeByte(if (isActive) 1 else 0)
        parcel.writeString(fullName)
        parcel.writeString(phoneNumber)
        parcel.writeString(lastLoginTime)
        parcel.writeString(creationTime)
        parcel.writeString(roleNames)
        parcel.writeInt(employeeId)
        parcel.writeString(gender)
        parcel.writeDouble(pickupLat)
        parcel.writeDouble(pickupLong)
        parcel.writeString(pickupAddress)
        parcel.writeDouble(dropLat)
        parcel.writeDouble(dropLong)
        parcel.writeString(dropAddress)
        parcel.writeString(city)
        parcel.writeString(department)
        parcel.writeString(entity)
        parcel.writeString(profilePicture)
        parcel.writeString(route)
        parcel.writeInt(routeId)
        parcel.writeString(directionPolyline)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Passenger> {
        override fun createFromParcel(parcel: Parcel): Passenger {
            return Passenger(parcel)
        }

        override fun newArray(size: Int): Array<Passenger?> {
            return arrayOfNulls(size)
        }
    }
}
