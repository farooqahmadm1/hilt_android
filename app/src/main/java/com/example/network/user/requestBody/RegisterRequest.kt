package com.ibex.fleetmanager.common.network.user.requestBody

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("name") val name: String, // Kamran
    @SerializedName("surname") val surname: String, // string
    @SerializedName("userName") val userName: String, // string
    @SerializedName("emailAddress") val emailAddress: String, // user@example.com
    @SerializedName("password") val password: String, // 12345678
    @SerializedName("employeeId") val employeeId: String, // 197924
    @SerializedName("phoneNumber") var phoneNumber: String, // 03040044245
    @SerializedName("gender") var gender: String, // male
    @SerializedName("city") var city: String,
    @SerializedName("pickupLat") var pickupLat: Double, // 31.510165
    @SerializedName("pickupLong") var pickupLong: Double, // 74.344142
    @SerializedName("pickupAddress") var pickAddress: String?,
    @SerializedName("dropLat") var dropLat: Double, // 31.459387
    @SerializedName("dropLong") var dropLong: Double, // 74.246565
    @SerializedName("dropAddress") var dropAddress: String?,
    @SerializedName("department") var department: String?, // Texh
    @SerializedName("entity") var entity: String?, // VW Tech
    @SerializedName("role") val role: Int, // 3
    @SerializedName("routeId") var routeId: Int // 3
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(surname)
        parcel.writeString(userName)
        parcel.writeString(emailAddress)
        parcel.writeString(password)
        parcel.writeString(employeeId)
        parcel.writeString(phoneNumber)
        parcel.writeString(gender)
        parcel.writeString(city)
        parcel.writeDouble(pickupLat)
        parcel.writeDouble(pickupLong)
        parcel.writeString(pickAddress)
        parcel.writeDouble(dropLat)
        parcel.writeDouble(dropLong)
        parcel.writeString(dropAddress)
        parcel.writeString(department)
        parcel.writeString(entity)
        parcel.writeInt(role)
        parcel.writeInt(routeId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RegisterRequest> {
        override fun createFromParcel(parcel: Parcel): RegisterRequest {
            return RegisterRequest(parcel)
        }

        override fun newArray(size: Int): Array<RegisterRequest?> {
            return arrayOfNulls(size)
        }
    }
}