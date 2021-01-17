package com.ibex.fleetmanager.common.network.user.requestBody


import com.google.gson.annotations.SerializedName

data class ChangePasswordRequest(
    @SerializedName("currentPassword") val currentPassword: String, // 12345678
    @SerializedName("newPassword") val newPassword: String // 123456
)