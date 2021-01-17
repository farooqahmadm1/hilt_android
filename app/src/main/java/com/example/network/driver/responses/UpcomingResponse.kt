package com.ibex.fleetmanager.common.network.driver.responses

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.ibex.fleetmanager.common.base.ErrorResponse
import com.ibex.fleetmanager.common.network.user.responses.Profile

data class UpcomingResponse(
    @SerializedName("result") val result: List<UpcomingTrip>?,
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


@Entity(tableName = "UpcomingTrip")
data class UpcomingTrip(
    @PrimaryKey
    @SerializedName("id") val id: Int, // 4
    @SerializedName("startTime") val startTime: String, // 2020-04-20T17:00:23.131+05:00
    @SerializedName("routeId") val routeId: Int, // 5
    @SerializedName("tripType") val type: Int,
    @SerializedName("tripStartingAddress") val startAddress: String, // 5
    @SerializedName("tripEndingAddress") val endAddress: String, // 5
    @SerializedName("passengers") val passengers: List<Profile>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.createTypedArrayList(Profile)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(startTime)
        parcel.writeInt(routeId)
        parcel.writeInt(type)
        parcel.writeString(startAddress)
        parcel.writeString(endAddress)
        parcel.writeTypedList(passengers)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UpcomingTrip> {
        override fun createFromParcel(parcel: Parcel): UpcomingTrip {
            return UpcomingTrip(parcel)
        }

        override fun newArray(size: Int): Array<UpcomingTrip?> {
            return arrayOfNulls(size)
        }
    }

}
