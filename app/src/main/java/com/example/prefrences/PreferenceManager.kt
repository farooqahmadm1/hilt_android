package com.ibex.fleetmanager.common.prefrences


import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.ibex.fleetmanager.common.Constants

class PreferenceManager(app: Application) {

    private var mPref: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(app)


    fun remove(key: String) {
        mPref.edit()
            .remove(key)
            .apply()
    }


    fun putString(key: String, value: String) {
        mPref.edit()
            .putString(key, value)
            .apply()
    }

    fun getString(key: String): String? {
        return mPref.getString(key, Constants.EMPTY_STRING)
    }

    fun getString(key: String, defaultValue: String): String? {
        return mPref.getString(key, defaultValue)
    }

    fun clear(): Boolean {
        return mPref.edit()
            .clear()
            .commit()
    }

    fun putBoolean(key: String, defaultValue: Boolean) {
        mPref.edit()
            .putBoolean(key, defaultValue)
            .apply()
    }

    fun getBoolean(key: String): Boolean {
        return mPref.getBoolean(key, false)
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return mPref.getBoolean(key, defaultValue)
    }

    fun putInt(key: String, value: Int) {
        mPref.edit()
            .putInt(key, value)
            .apply()
    }

    fun getInt(key: String): Int {
        return mPref.getInt(key, 0)
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return mPref.getInt(key, defaultValue)
    }

    fun getLong(key: String, defaultValue: Long): Long {
        return mPref.getLong(key, defaultValue)
    }

    fun getLong(key: String): Long {
        return mPref.getLong(key, 1)
    }

    fun putLong(key: String, value: Long) {
        mPref.edit()
            .putLong(key, value)
            .apply()
    }
}