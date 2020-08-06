package com.example.hilt_android.ui.home_fragment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.hilt_android.util.listLiveResponse


class HomeViewModel @ViewModelInject constructor(
    private val repo: HomeRepo
) : ViewModel() {

    fun getUsers() = listLiveResponse { repo.getAllUsers() }

    fun getUser(id: Int) = repo.loadUser(id)
}