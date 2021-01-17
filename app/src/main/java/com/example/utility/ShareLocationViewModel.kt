package com.ibex.fleetmanager.driver.utility

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShareLocationViewModel : ViewModel() {
    val locationLiveData = MutableLiveData<Location>()

    fun getCurrentLocation(item: Location) {
        locationLiveData.value = item
    }
}