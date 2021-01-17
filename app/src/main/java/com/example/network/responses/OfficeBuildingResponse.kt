package com.ibex.fleetmanager.common.network.responses

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.ibex.fleetmanager.common.base.ErrorResponse

data class OfficeBuildingResponse(
    @SerializedName("result") val result: OfficeBuildings?,
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

data class OfficeBuildings(
    @SerializedName("totalCount") val totalCount: Int,
    @SerializedName("items") val items: List<OfficeBuildingItems>
)

data class OfficeBuildingItems(
    @SerializedName("name") val name: String,
    @SerializedName("city") val city: String,
    @SerializedName("lat") val lat: Double,
    @SerializedName("long") val long: Double,
    @SerializedName("id") val id: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(city)
        parcel.writeDouble(lat)
        parcel.writeDouble(long)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OfficeBuildingItems> {
        override fun createFromParcel(parcel: Parcel): OfficeBuildingItems {
            return OfficeBuildingItems(parcel)
        }

        override fun newArray(size: Int): Array<OfficeBuildingItems?> {
            return arrayOfNulls(size)
        }
    }
}

data class SelectedLocationAddress(
    val address: String,
    val lat: Double,
    val lng: Double,
    var polyline: String
)