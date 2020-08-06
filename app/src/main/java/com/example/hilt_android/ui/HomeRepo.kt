package com.example.hilt_android.ui

import androidx.lifecycle.LiveData
import com.example.hilt_android.db.UserDao
import com.example.hilt_android.model.UserResponse
import com.example.hilt_android.network.BaseRemoteRepository
import com.example.hilt_android.network.ErrorType
import com.example.hilt_android.network.ResponseResult
import com.example.hilt_android.network.RestApiServices
import com.example.hilt_android.practice_network.AppExecutors
import com.example.hilt_android.practice_network.NetworkBoundResource
import com.example.hilt_android.practice_network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepo @Inject constructor(
    private val apiServices: RestApiServices,
    private val userDao: UserDao,
    private val appExecutors: AppExecutors
) : BaseRemoteRepository() {

    suspend fun getAllUsers() = withContext(Dispatchers.IO) {
        when (val obj = safeApiCall { apiServices.getPostList() }!!) {
            is ResponseResult.Success -> {
                userDao.insert(obj.data)
                ResponseResult.Success(obj.data)
            }
            is ResponseResult.Error -> {
                ResponseResult.Error(obj.message, obj.type)
            }
            else -> ResponseResult.Error("", ErrorType.UNKNOWN)
        }
    }

    fun loadUser(login: Int): LiveData<Resource<UserResponse>> {
        return object : NetworkBoundResource<UserResponse, UserResponse>(appExecutors) {
            override fun saveCallResult(item: UserResponse) {
                userDao.insertUser(item)
            }

            override fun shouldFetch(data: UserResponse?) = true

            override fun loadFromDb() = userDao.getUser(login)

            override fun createCall() = apiServices.getUser(login)
        }.asLiveData()
    }
}