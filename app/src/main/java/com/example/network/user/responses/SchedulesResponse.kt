package com.ibex.fleetmanager.common.network.user.responses

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.ibex.fleetmanager.common.base.ErrorResponse
import com.ibex.fleetmanager.common.network.requestBody.ScheduleSpecifics

data class SchedulesResponse(
    @SerializedName("result") val result: SchedulesListResultObject?,
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

data class SchedulesListResultObject(

    @SerializedName("totalCount") val totalCount: Int,
    @SerializedName("items") val items: List<SchedulesItems>
)

data class SchedulesItems(
    @SerializedName("scheduleStartDate") val scheduleStartDate: String,
    @SerializedName("scheduleEndDate") val scheduleEndDate: String,
    @SerializedName("serviceType") val serviceType: Int,
    @SerializedName("pickupLat") val pickupLat: Double,
    @SerializedName("pickupLong") val pickupLong: Double,
    @SerializedName("dropLat") val dropLat: Double,
    @SerializedName("dropLong") val dropLong: Double,
    @SerializedName("userName") val userName: String,
    @SerializedName("userId") val userId: Int,
    @SerializedName("user") val user: String,
    @SerializedName("scheduleStatus") val scheduleStatus: Int,
    @SerializedName("tenantId") val tenantId: Int,
    @SerializedName("scheduleSpecifics") val scheduleSpecifics: List<ScheduleSpecifics>,
    @SerializedName("id") val id: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.createTypedArrayList(ScheduleSpecifics)!!,
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(scheduleStartDate)
        parcel.writeString(scheduleEndDate)
        parcel.writeInt(serviceType)
        parcel.writeDouble(pickupLat)
        parcel.writeDouble(pickupLong)
        parcel.writeDouble(dropLat)
        parcel.writeDouble(dropLong)
        parcel.writeString(userName)
        parcel.writeInt(userId)
        parcel.writeString(user)
        parcel.writeInt(scheduleStatus)
        parcel.writeInt(tenantId)
        parcel.writeTypedList(scheduleSpecifics)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SchedulesItems> {
        override fun createFromParcel(parcel: Parcel): SchedulesItems {
            return SchedulesItems(parcel)
        }

        override fun newArray(size: Int): Array<SchedulesItems?> {
            return arrayOfNulls(size)
        }
    }
}
