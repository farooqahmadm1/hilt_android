package com.example.hilt_android.util

import com.example.hilt_android.util.base.BaseApplication

object Utils {

    fun getString(resource: Int) = BaseApplication.appContext.getString(resource)

//    fun buildImageString(path: String) = "${Constants.BASE_IMG_URL}${path}"
}