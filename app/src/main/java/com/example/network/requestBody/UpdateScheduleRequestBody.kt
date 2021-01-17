package com.ibex.fleetmanager.common.network.requestBody

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class UpdateScheduleRequestBody(
    @SerializedName("scheduleStartDate") var scheduleStartDate: String,
    @SerializedName("scheduleEndDate") var scheduleEndDate: String,
    @SerializedName("serviceType") var serviceType: Int,
    @SerializedName("scheduleSpecifics") val scheduleSpecifics: MutableList<UpdateScheduleSpecifics>,
    @SerializedName("id") var id: Int
)

data class UpdateScheduleSpecifics(
    @SerializedName("weekday") val weekday: String,
    @SerializedName("pickupTime") var pickupTime: String,
    @SerializedName("pickupTripId") var pickupTripId: Int,
    @SerializedName("dropoffTime") var dropoffTime: String,
    @SerializedName("dropoffTripId") var dropoffTripId: Int,
    @SerializedName("id") var id: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(weekday)
        parcel.writeString(pickupTime)
        parcel.writeInt(pickupTripId)
        parcel.writeString(dropoffTime)
        parcel.writeInt(dropoffTripId)
        parcel.writeString(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ScheduleSpecifics> {
        override fun createFromParcel(parcel: Parcel): ScheduleSpecifics {
            return ScheduleSpecifics(parcel)
        }

        override fun newArray(size: Int): Array<ScheduleSpecifics?> {
            return arrayOfNulls(size)
        }
    }
}