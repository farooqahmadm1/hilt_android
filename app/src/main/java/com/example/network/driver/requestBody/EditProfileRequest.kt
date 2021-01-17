package com.ibex.fleetmanager.common.network.driver.requestBody


import com.google.gson.annotations.SerializedName

data class EditProfileRequest(
    @SerializedName("id") val id: Int, // 0
    @SerializedName("name") val name: String, // string
    @SerializedName("surname") val surname: String, // string
    @SerializedName("emailAddress") val email: String,
    @SerializedName("gender") val gender: String, // string
    @SerializedName("cnicNumber") val cnicNumber: String, // string
    @SerializedName("phoneNumber") val phoneNumber: String, // string
    @SerializedName("licenseNumber") val licenseNumber: String // string
)