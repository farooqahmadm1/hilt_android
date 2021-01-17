package com.ibex.fleetmanager.common.network.driver.requestBody


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegisterRequest(
    @SerializedName("name") val name: String = "", // Farooq
    @SerializedName("surname") val surname: String = "", // string
    @SerializedName("userName") var userName: String = "", // string
    @SerializedName("emailAddress") var emailAddress: String = "", // farooqahmad@gmail.com
    @SerializedName("password") val password: String = "", // 12345678
    @SerializedName("phoneNumber") var phoneNumber: String = "", // 03040044245
    @SerializedName("gender") var gender: String = "", // Male
    @SerializedName("role") var role: Int = 2, // 2
    @SerializedName("cnicNumber") var cnicNumber: String = "", // 90802934802
    @SerializedName("licenseNumber") var licenseNumber: String = "" // LEA314
) : Parcelable