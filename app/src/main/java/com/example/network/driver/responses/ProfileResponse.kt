package com.ibex.fleetmanager.common.network.driver.responses


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.ibex.fleetmanager.common.base.ErrorResponse

data class ProfileResponse(
    @SerializedName("result") val result: ProfileDriver?,
    @SerializedName("targetUrl") val targetUrl: String?, // null
    @SerializedName("success") val _success: Boolean?, // true
    @SerializedName("error") val error: ErrorResponse?, // null
    @SerializedName("unAuthorizedRequest") val unAuthorizedRequest: Boolean, // false
    @SerializedName("__abp") val abp: Boolean? // true
) {
    val success
        get() = _success
            ?: throw IllegalArgumentException("Success value is required.Found Null!")
}

@Entity(tableName = "dprofile")
data class ProfileDriver(
    @PrimaryKey
    @SerializedName("id") val id: Int,
    @SerializedName("userName") val userName: String? = "", // shafique@ibex.co
    @SerializedName("name") val name: String? = "", // Shafique
    @SerializedName("surname") val surname: String? = "", // Rehman
    @SerializedName("emailAddress") val emailAddress: String? = "", // shafique@ibex.co
    @SerializedName("isActive") val isActive: Boolean, // true
    @SerializedName("fullName") val fullName: String? = "", // Shafique Rehman
    @SerializedName("phoneNumber") val phoneNumber: String? = "", // 03314856389
    @SerializedName("lastLoginTime") val lastLoginTime: String? = "", // null
    @SerializedName("creationTime") val creationTime: String? = "", // 2020-04-06T11:01:13.9084341
    @SerializedName("roleNames") val roleNames: List<String>?, // null
    @SerializedName("gender") val gender: String? = "", // Male
    @SerializedName("profilePicture") val profilePicture: String? = "", // null
    @SerializedName("city") val city: String? = "", // Lahore
    @SerializedName("cnic") val cnic: String? = "", // null
    @SerializedName("cnicNumber") val cnicNumber: String? = "", // 3520228241441
    @SerializedName("license") val license: String? = "", // null
    @SerializedName("licenseNumber") val licenseNumber: String? = "", // LHR18129
    @SerializedName("vehicleId") val vehicleId: Int? = 0, // 2
    @SerializedName("vehicle") val vehicle: Vehicle?
)


@Entity(tableName = "vehicle")
data class Vehicle(
    @PrimaryKey
    @SerializedName("id") val id: Int, // 2
    @SerializedName("make") val make: String? = "", // Suzuki
    @SerializedName("model") val model: String? = "", // Cultus
    @SerializedName("color") val color: String? = "", // Cultus
    @SerializedName("registrationNumber") val registrationNumber: String? = "", // LE-5943
    @SerializedName("capacity") val capacity: Int? = 0, // 4
    @SerializedName("type") val type: String? = "", // SUV
    @SerializedName("tenantId") val tenantId: Int? = 0, // 1
    @SerializedName("vehicleImage") val vehicleImage: String? = "" // null
)