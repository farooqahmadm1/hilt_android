package com.example.hilt_android.util.base

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    companion object {

        private lateinit var appInstance: BaseApplication
        val appContext: Context by lazy {
            appInstance.applicationContext
        }
    }

}