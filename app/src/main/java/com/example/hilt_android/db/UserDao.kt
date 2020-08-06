package com.example.hilt_android.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hilt_android.model.UserResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userResponse: UserResponse)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<UserResponse>)

    @Query("SELECT * FROM USER Where id =:param ")
    fun getUser(param: Int): LiveData<UserResponse>

    @Query("SELECT * FROM USER")
    fun getUserList(): Flow<List<UserResponse>>
}