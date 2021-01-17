package com.ibex.fleetmanager.common.network.responses

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.ibex.fleetmanager.common.base.ErrorResponse

data class TripsResponse(
    @SerializedName("result") val result: TripsListResult?,
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

data class TripsListResult(
    @SerializedName("totalCount") val totalCount: Int,
    @SerializedName("items") val items: List<TripsItems>
)

data class TripsItems(
    @SerializedName("name") val name: String,
    @SerializedName("startTime") val startTime: String,
    @SerializedName("tripStartAddress") val tripStartAddress: String,
    @SerializedName("tripEndAddress") val tripEndAddress: String,
    @SerializedName("tripStatus") val tripStatus: Int,
    @SerializedName("type") val type: Int,
    @SerializedName("route") val route: RoutesItems,
    @SerializedName("routeId") val routeId: Int,
    @SerializedName("passengerCount") val passengerCount: Int,
    @SerializedName("driverCount") val driverCount: Int,
    @SerializedName("vehicle") val vehicle: Vehicle,
    @SerializedName("id") val id: Int
)

data class Vehicle(

    @SerializedName("make") val make: String,
    @SerializedName("model") val model: String,
    @SerializedName("registrationNumber") val registrationNumber: String,
    @SerializedName("capacity") val capacity: Int,
    @SerializedName("type") val type: String,
    @SerializedName("color") val color: String,
    @SerializedName("tenantId") val tenantId: Int,
    @SerializedName("vehicleImage") val vehicleImage: String,
    @SerializedName("id") val id: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(make)
        parcel.writeString(model)
        parcel.writeString(registrationNumber)
        parcel.writeInt(capacity)
        parcel.writeString(type)
        parcel.writeString(color)
        parcel.writeInt(tenantId)
        parcel.writeString(vehicleImage)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Vehicle> {
        override fun createFromParcel(parcel: Parcel): Vehicle {
            return Vehicle(parcel)
        }

        override fun newArray(size: Int): Array<Vehicle?> {
            return arrayOfNulls(size)
        }
    }
}