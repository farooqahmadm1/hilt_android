package com.example.hilt_android.ui

import com.example.hilt_android.db.UserDao
import com.example.hilt_android.network.RestApiServices
import com.example.hilt_android.util.safeApiCall
import javax.inject.Inject

class HomeRepo @Inject constructor(
    private val apiServices: RestApiServices,
    private val userDao: UserDao
) {
    suspend fun getAllUsers() = safeApiCall { apiServices.getPostList() }
}

