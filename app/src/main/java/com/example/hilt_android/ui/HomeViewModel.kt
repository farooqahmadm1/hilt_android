package com.example.hilt_android.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.hilt_android.util.liveResponse


class HomeViewModel @ViewModelInject constructor(
    private val repo: HomeRepo
) : ViewModel() {

    fun getUsers() = liveResponse { repo.getAllUsers() }
}