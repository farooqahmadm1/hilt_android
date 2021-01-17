package com.ibex.fleetmanager.common.network.responses

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.ibex.fleetmanager.common.base.ErrorResponse

data class RoutesResponse(
    @SerializedName("result") val listResult: RoutesListResult?,
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

data class RoutesListResult(
    @SerializedName("totalCount") val totalCount: Int,
    @SerializedName("items") val items: List<RoutesItems>
)

data class RoutesItems(
    @SerializedName("name") val name: String,
    @SerializedName("city") val city: String,
    @SerializedName("startingPointLat") val startingPointLat: Double,
    @SerializedName("startingPointLong") val startingPointLong: Double,
    @SerializedName("endingPointLat") val endingPointLat: Double,
    @SerializedName("endingPointLong") val endingPointLong: Double,
    @SerializedName("startingAddress") val startingAddress: String,
    @SerializedName("endingAddress") val endingAddress: String,
    @SerializedName("isActive") val isActive: Boolean,
    @SerializedName("id") val id: Int,
    var isSelected: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(city)
        parcel.writeDouble(startingPointLat)
        parcel.writeDouble(startingPointLong)
        parcel.writeDouble(endingPointLat)
        parcel.writeDouble(endingPointLong)
        parcel.writeString(startingAddress)
        parcel.writeString(endingAddress)
        parcel.writeByte(if (isActive) 1 else 0)
        parcel.writeInt(id)
        parcel.writeByte(if (isSelected) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RoutesItems> {
        override fun createFromParcel(parcel: Parcel): RoutesItems {
            return RoutesItems(parcel)
        }

        override fun newArray(size: Int): Array<RoutesItems?> {
            return arrayOfNulls(size)
        }
    }
}