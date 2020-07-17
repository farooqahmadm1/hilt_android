package com.example.hilt_android.db

import androidx.room.TypeConverter
import com.example.hilt_android.model.UserResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

object TypeConverterManager {

    private val gson = Gson()

    @JvmStatic
    @TypeConverter
    fun fromUserResponse(list : List<UserResponse>) : String?{
        return gson.toJson(list)
    }

    @JvmStatic
    @TypeConverter
    fun stringToUserResponse(data : String): List<UserResponse>?{
        val type  = object : TypeToken<List<UserResponse>>(){}.type
        return gson.fromJson(data,type)
    }


}