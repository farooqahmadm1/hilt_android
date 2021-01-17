package com.ibex.fleetmanager.common.network.user.responses

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.ibex.fleetmanager.common.base.ErrorResponse


data class SettingsResponse(
    @SerializedName("result") val result: List<SettingsItems>?,
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

@Entity(tableName = "settings")
data class SettingsItems(
    @PrimaryKey
    @field:SerializedName("name") val name: String,
    @field:SerializedName("value") val value: Boolean
)
