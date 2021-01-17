package com.ibex.fleetmanager.common.network.user.responses

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.ibex.fleetmanager.common.base.ErrorResponse
import com.ibex.fleetmanager.common.network.responses.RoutesItems

data class ProfileResponse(
    @SerializedName("result") val result: Profile?,
    @SerializedName("targetUrl") val targetUrl: String? = "",
    @SerializedName("success") val _success: Boolean?,
    @SerializedName("error") val error: ErrorResponse?,
    @SerializedName("unAuthorizedRequest") val unAuthorizedRequest: Boolean?,
    @SerializedName("__abp") val __abp: Boolean?
) {
    val success
        get() = _success
            ?: throw IllegalArgumentException("Success value is required.Found Null!")
}

@Entity(tableName = "profile")
data class Profile(
    @field:SerializedName("userName") val userName: String? = "",
    @field:SerializedName("name") var name: String? = "",
    @field:SerializedName("surname") var surname: String? = "",
    @field:SerializedName("emailAddress") var emailAddress: String? = "",
    @field:SerializedName("isActive") val isActive: Boolean,
    @field:SerializedName("fullName") val fullName: String? = "",
    @field:SerializedName("phoneNumber") var phoneNumber: String? = "",
    @field:SerializedName("lastLoginTime") val lastLoginTime: String? = "",
    @field:SerializedName("creationTime") val creationTime: String? = "",
    @field:SerializedName("employeeId") val employeeId: Int? = 0,
    @field:SerializedName("gender") val gender: String? = "",
    @field:SerializedName("pickupLat") var pickupLat: Double? = 0.0,
    @field:SerializedName("pickupLong") var pickupLong: Double? = 0.0,
    @field:SerializedName("pickupAddress") var pickupAddress: String? = "",
    @field:SerializedName("dropLat") var dropLat: Double? = 0.0,
    @field:SerializedName("dropLong") var dropLong: Double? = 0.0,
    @field:SerializedName("dropAddress") var dropAddress: String? = "",
    @field:SerializedName("department") var department: String? = "",
    @field:SerializedName("city") var city: String? = "",
    @field:SerializedName("entity") var entity: String? = "",
    @field:SerializedName("profilePicture") val profilePicture: String? = "",
    @field:SerializedName("route") var route: RoutesItems?,
    @field:SerializedName("cnic") val cnic: String? = "",
    @field:SerializedName("cnicNumber") val cnicNumber: String? = "",
    @field:SerializedName("license") val license: String? = "",
    @field:SerializedName("licenseNumber") val licenseNumber: String? = "",
    @field:SerializedName("routeId") var routeId: Int?,
    @field:SerializedName("directionPolyline") var directionPolyline: String? = "",
    @field:SerializedName("serviceType") var serviceType: Int,
    @field:SerializedName("isProfileApproved") var isProfileApproved: Boolean,
    @PrimaryKey
    @field:SerializedName("id") val id: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(RoutesItems::class.java.classLoader),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
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
        parcel.writeValue(employeeId)
        parcel.writeString(gender)
        parcel.writeValue(pickupLat)
        parcel.writeValue(pickupLong)
        parcel.writeString(pickupAddress)
        parcel.writeValue(dropLat)
        parcel.writeValue(dropLong)
        parcel.writeString(dropAddress)
        parcel.writeString(department)
        parcel.writeString(city)
        parcel.writeString(entity)
        parcel.writeString(profilePicture)
        parcel.writeParcelable(route, flags)
        parcel.writeString(cnic)
        parcel.writeString(cnicNumber)
        parcel.writeString(license)
        parcel.writeString(licenseNumber)
        parcel.writeValue(routeId)
        parcel.writeString(directionPolyline)
        parcel.writeInt(serviceType)
        parcel.writeByte(if (isActive) 1 else 0)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Profile> {
        override fun createFromParcel(parcel: Parcel): Profile {
            return Profile(parcel)
        }

        override fun newArray(size: Int): Array<Profile?> {
            return arrayOfNulls(size)
        }
    }
}
