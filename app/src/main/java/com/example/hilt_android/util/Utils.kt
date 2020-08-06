package com.example.hilt_android.util

object Utils {

    fun getString(resource: Int) = BaseApplication.appContext.getString(resource)

//    fun buildImageString(path: String) = "${Constants.BASE_IMG_URL}${path}"
}