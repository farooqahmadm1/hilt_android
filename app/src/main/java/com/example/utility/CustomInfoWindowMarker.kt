package com.ibex.fleetmanager.common.utility


import android.app.Activity
import android.content.Context
import android.view.View
import androidx.databinding.DataBindingUtil
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.ibex.fleetmanager.common.R
import com.ibex.fleetmanager.common.databinding.MarkerInfoWindowBinding
import com.ibex.fleetmanager.common.model.MarkerInfoWindow


class CustomInfoWindowMarker(val context: Context) : GoogleMap.InfoWindowAdapter {
    override fun getInfoContents(marker: Marker): View? {
        return null
    }

    override fun getInfoWindow(marker: Marker): View? {

        val view: MarkerInfoWindowBinding = DataBindingUtil.inflate(
            (context as Activity).layoutInflater, R.layout.marker_info_window, null, false
        )

        marker.tag?.let { tag ->
            val infoWindowData: MarkerInfoWindow? = tag as MarkerInfoWindow
            infoWindowData?.let {
                when (infoWindowData.type) {
                    MarkerType.START.value -> {
                        view.text.text = "Pick Up : ${infoWindowData.time}"
                        view.root.setBackgroundColor(context.resources.getColor(R.color.white))
                    }
                    MarkerType.END.value -> {
                        view.text.text = "ETA : ${infoWindowData.duration}"
                        view.text.setTextColor(context.resources.getColor(R.color.white))
                        view.root.setBackgroundColor(context.resources.getColor(R.color.button_red))
                    }
                    else -> {

                    }
                }
            }
        }

        return view.root
    }
}