package com.ibex.fleetmanager.common.network.user.responses

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.ibex.fleetmanager.common.base.ErrorResponse

data class PastSchduleResponse(
    @SerializedName("result") val result: PastScheduleListResult?,
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

data class PastScheduleListResult(

    @SerializedName("totalCount") val totalCount: Int,
    @SerializedName("items") val items: List<PastScheduleItems>
)


data class PastScheduleItems(
    @SerializedName("creationTime") val creationTime: String,
    @SerializedName("employeeId") val employeeId: Int,
    @SerializedName("fullName") val fullName: String,
    @SerializedName("tripId") val tripId: Int,
    @SerializedName("routeId") val routeId: Int,
    @SerializedName("tripName") val tripName: String,
    @SerializedName("routeName") val routeName: String,
    @SerializedName("driverName") val driverName: String,
    @SerializedName("pickupTime") val pickupTime: String,
    @SerializedName("dropTime") val dropTime: String,
    @SerializedName("pickupLocation") val pickupLocation: String,
    @SerializedName("dropLocation") val dropLocation: String,
    @SerializedName("status") val status: Int,
    @SerializedName("tripType") val tripType: Int,
    @SerializedName("id") val id: String,
    var viewType: Int = 1
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readInt()
    ) {
    }

    constructor(viewTypeParam: Int) : this(
        creationTime = "",
        employeeId = 0,
        fullName = "",
        tripId = 0,
        routeId = 0,
        tripName = "",
        routeName = "",
        driverName = "",
        pickupTime = "",
        dropTime = "",
        pickupLocation = "",
        dropLocation = "",
        status = 0,
        tripType = 0,
        id = "0",
        viewType = viewTypeParam
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(creationTime)
        parcel.writeInt(employeeId)
        parcel.writeString(fullName)
        parcel.writeInt(tripId)
        parcel.writeInt(routeId)
        parcel.writeString(tripName)
        parcel.writeString(routeName)
        parcel.writeString(driverName)
        parcel.writeString(pickupTime)
        parcel.writeString(dropTime)
        parcel.writeString(pickupLocation)
        parcel.writeString(dropLocation)
        parcel.writeInt(status)
        parcel.writeInt(tripType)
        parcel.writeString(id)
        parcel.writeInt(viewType)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PastScheduleItems> {
        override fun createFromParcel(parcel: Parcel): PastScheduleItems {
            return PastScheduleItems(parcel)
        }

        override fun newArray(size: Int): Array<PastScheduleItems?> {
            return arrayOfNulls(size)
        }
    }


}
