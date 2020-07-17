package com.example.hilt_android.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hilt_android.model.UserResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userResponse: UserResponse)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<UserResponse>)

    @Query("SELECT * FROM USER Where id =:param ")
    fun getUser(param: Int): Flow<UserResponse>

    @Query("SELECT * FROM USER")
    fun getUserList(): Flow<List<UserResponse>>
}