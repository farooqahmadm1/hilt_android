package com.example.hilt_android.network

import androidx.lifecycle.LiveData
import com.example.hilt_android.model.UserResponse
import com.example.hilt_android.practice_network.ApiResponse
import kotlinx.coroutines.flow.Flow

import retrofit2.http.GET
import retrofit2.http.Path

interface RestApiServices {
    @GET("posts/{id}")
    fun getUser(@Path("id") id: Int): LiveData<ApiResponse<UserResponse>>

    @GET("posts")
    suspend fun getPostList(): List<UserResponse>

    @GET("posts")
    fun getPostLists(): Flow<ApiResponse<List<UserResponse>>>
}