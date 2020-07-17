package com.example.hilt_android.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "user")
data class UserResponse (
    @PrimaryKey
    @SerializedName("id") val id : Int,
    @SerializedName("userId") val userId: String,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String
)