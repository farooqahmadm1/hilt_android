package com.ibex.fleetmanager.common.network.user.responses

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.ibex.fleetmanager.common.base.ErrorResponse
import com.ibex.fleetmanager.common.network.responses.RoutesItems
import com.ibex.fleetmanager.common.network.responses.Vehicle

data class TripExecutionDataResponse(
    @SerializedName("result") val result: TripExecutionDataResult?,
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

data class TripExecutionDataResult(
    @SerializedName("trip") val trip: TripDetails,
    @SerializedName("driver") val driver: DriverDetails,
    @SerializedName("startTime") val startTime: String,
    @SerializedName("endTime") val endTime: String,
    @SerializedName("type") val type: Int,
    @SerializedName("status") val status: Int,
    @SerializedName("tripExecutionDrivers") var tripExecutionDrivers: List<TripExecutionDrivers>,
    @SerializedName("id") val id: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(TripDetails::class.java.classLoader)!!,
        parcel.readParcelable(DriverDetails::class.java.classLoader)!!,
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.createTypedArrayList(TripExecutionDrivers)!!,
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(trip, flags)
        parcel.writeParcelable(driver, flags)
        parcel.writeString(startTime)
        parcel.writeString(endTime)
        parcel.writeInt(type)
        parcel.writeInt(status)
        parcel.writeTypedList(tripExecutionDrivers)
        parcel.writeString(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TripExecutionDataResult> {
        override fun createFromParcel(parcel: Parcel): TripExecutionDataResult {
            return TripExecutionDataResult(parcel)
        }

        override fun newArray(size: Int): Array<TripExecutionDataResult?> {
            return arrayOfNulls(size)
        }
    }
}

data class TripDetails(
    @SerializedName("creationTime") val creationTime: String,
    @SerializedName("name") val name: String,
    @SerializedName("startTime") val startTime: String,
    @SerializedName("route") val route: RoutesItems,
    @SerializedName("routeId") val routeId: Int,
    @SerializedName("tripId") val tripId: Int,
    @SerializedName("type") val type: Int,
    @SerializedName("driverId") val driverId: Int,
    @SerializedName("isActive") val isActive: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readParcelable(RoutesItems::class.java.classLoader)!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(creationTime)
        parcel.writeString(name)
        parcel.writeString(startTime)
        parcel.writeParcelable(route, flags)
        parcel.writeInt(routeId)
        parcel.writeInt(tripId)
        parcel.writeInt(type)
        parcel.writeInt(driverId)
        parcel.writeByte(if (isActive) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TripDetails> {
        override fun createFromParcel(parcel: Parcel): TripDetails {
            return TripDetails(parcel)
        }

        override fun newArray(size: Int): Array<TripDetails?> {
            return arrayOfNulls(size)
        }
    }
}

data class DriverDetails(

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
    @SerializedName("gender") val gender: String,
    @SerializedName("profilePicture") val profilePicture: String,
    @SerializedName("city") val city: String,
    @SerializedName("cnic") val cnic: String,
    @SerializedName("cnicNumber") val cnicNumber: String,
    @SerializedName("license") val license: String,
    @SerializedName("licenseNumber") val licenseNumber: String,
    @SerializedName("vehicleId") val vehicleId: Int,
    @SerializedName("vehicle") val vehicle: Vehicle,
    @SerializedName("id") val id: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readParcelable(Vehicle::class.java.classLoader)!!,
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
        parcel.writeString(gender)
        parcel.writeString(profilePicture)
        parcel.writeString(city)
        parcel.writeString(cnic)
        parcel.writeString(cnicNumber)
        parcel.writeString(license)
        parcel.writeString(licenseNumber)
        parcel.writeInt(vehicleId)
        parcel.writeParcelable(vehicle, flags)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DriverDetails> {
        override fun createFromParcel(parcel: Parcel): DriverDetails {
            return DriverDetails(parcel)
        }

        override fun newArray(size: Int): Array<DriverDetails?> {
            return arrayOfNulls(size)
        }
    }
}

data class TripExecutionDrivers(
    @SerializedName("passenger") val passenger: Passenger,
    @SerializedName("tripExecutionId") val tripExecutionId: String,
    @SerializedName("passengerId") val passengerId: Int,
    @SerializedName("pickupTime") val pickupTime: String,
    @SerializedName("dropTime") val dropTime: String,
    @SerializedName("status") val status: Int,
    @SerializedName("id") val id: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Passenger::class.java.classLoader)!!,
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(passenger, flags)
        parcel.writeString(tripExecutionId)
        parcel.writeInt(passengerId)
        parcel.writeString(pickupTime)
        parcel.writeString(dropTime)
        parcel.writeInt(status)
        parcel.writeString(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TripExecutionDrivers> {
        override fun createFromParcel(parcel: Parcel): TripExecutionDrivers {
            return TripExecutionDrivers(parcel)
        }

        override fun newArray(size: Int): Array<TripExecutionDrivers?> {
            return arrayOfNulls(size)
        }
    }
}