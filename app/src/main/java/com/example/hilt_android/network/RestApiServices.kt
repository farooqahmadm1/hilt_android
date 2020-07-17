package com.example.hilt_android.network

import com.example.hilt_android.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface RestApiServices {
    @GET("posts/{id}")
    suspend fun getUser(@Path("id") id : Int) : UserResponse

    @GET("posts")
    suspend fun getPostList() : List<UserResponse>
}