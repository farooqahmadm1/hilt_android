package com.ibex.fleetmanager.common.network.user.responses

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.ibex.fleetmanager.common.base.ErrorResponse

data class RequestsListResponse(
    @SerializedName("result") val result: RequestsListResultObject?,
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

data class RequestsListResultObject(
    @SerializedName("totalCount") val totalCount: Int,
    @SerializedName("items") val items: List<RequestListItem>
)

data class RequestListItem(
    @SerializedName("category") val category: Int,
    @SerializedName("subCategory") val subCategory: Int,
    @SerializedName("status") val status: Int,
    @SerializedName("requestNumber") val requestNumber: Int,
    @SerializedName("creationTime") val creationTime: String,
    @SerializedName("id") val id: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(category)
        parcel.writeInt(subCategory)
        parcel.writeInt(status)
        parcel.writeInt(requestNumber)
        parcel.writeString(creationTime)
        parcel.writeString(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RequestListItem> {
        override fun createFromParcel(parcel: Parcel): RequestListItem {
            return RequestListItem(parcel)
        }

        override fun newArray(size: Int): Array<RequestListItem?> {
            return arrayOfNulls(size)
        }
    }
}