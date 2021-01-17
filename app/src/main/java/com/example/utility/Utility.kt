package com.ibex.fleetmanager.driver.utility

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Point
import android.location.Location
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.os.Handler
import android.os.SystemClock
import android.preference.PreferenceManager
import android.provider.Settings
import android.view.View
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.Projection
import com.google.android.gms.maps.model.*
import com.ibex.fleetmanager.common.R
import com.ibex.fleetmanager.common.model.LocalLocation
import com.ibex.fleetmanager.common.network.driver.responses.DirectionsDriverRouteResponse
import com.ibex.fleetmanager.common.network.driver.responses.TripExecutionDriver
import com.ibex.fleetmanager.common.network.driver.responses.TripStart
import com.ibex.fleetmanager.common.utility.BoardStatus
import com.ibex.fleetmanager.common.utility.toOneDecimal
import java.text.DateFormat
import java.util.*
import java.util.stream.Collectors


internal object Utility {
    const val KEY_REQUESTING_LOCATION_UPDATES = "requesting_location_updates"

    /**
     * Returns true if requesting location updates, otherwise returns false.
     *
     * @param context The [Context].
     */
    fun requestingLocationUpdates(context: Context?): Boolean {
        return PreferenceManager.getDefaultSharedPreferences(context)
            .getBoolean(KEY_REQUESTING_LOCATION_UPDATES, false)
    }

    /**
     * Stores the location updates state in SharedPreferences.
     * @param requestingLocationUpdates The location updates state.
     */
    fun setRequestingLocationUpdates(
        context: Context?,
        requestingLocationUpdates: Boolean
    ) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putBoolean(KEY_REQUESTING_LOCATION_UPDATES, requestingLocationUpdates)
            .apply()
    }

    /**
     * Returns the `location` object as a human readable string.
     * @param location  The [Location].
     */
    fun getLocationText(location: Location?): String {
        return if (location == null) "Unknown location" else "(" + location.latitude + ", " + location.longitude + ")"
    }

    fun getLocationTitle(context: Context): String {
        return context.getString(
            R.string.location_updated,
            DateFormat.getDateTimeInstance().format(Date())
        )
    }

    fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        return ContextCompat.getDrawable(context, vectorResId)?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap =
                Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }

    fun findNextPassenger(it: TripStart): TripExecutionDriver? {
        it.tripExecutionDrivers?.forEach { user ->
            if (user.status == BoardStatus.PENDING.value) {
                return user
            }
        }
        return null
    }

    fun findNextDestinationPassenger(it: TripStart): TripExecutionDriver? {
        it.tripExecutionDrivers?.forEach { user ->
            if (user.status == BoardStatus.CHECK_IN.value) {
                return user
            }
        }
        return null
    }

    fun findNextPassenger(trip: TripStart, wayOrder: List<Int>): TripExecutionDriver? {
        trip.tripExecutionDrivers?.let {
            sortListAccordingToDirection(it, wayOrder).forEach { user ->
                if (user.status == BoardStatus.PENDING.value) {
                    return user
                }
            }
        }
        return null
    }

    fun findNextDestinationPassenger(trip: TripStart, wayOrder: List<Int>): TripExecutionDriver? {
        trip.tripExecutionDrivers?.let {
            sortListAccordingToDirection(it, wayOrder).forEach { user ->
                if (user.status == BoardStatus.CHECK_IN.value) {
                    return user
                }
            }
        }
        return null
    }

    //call in home or service fragment
    fun checkIfAnyCheckedIn(tripData: TripStart): Boolean {
        tripData.tripExecutionDrivers?.forEach {
            if (it.status == BoardStatus.CHECK_IN.value) {
                return true
            }
        }
        return false
    }

    //call in service fragment
    fun checkIfAnyArrivedStatus(tripData: TripStart): Boolean {
        tripData.tripExecutionDrivers?.forEach {
            if (!it.driverArrived) {
                return true
            }
        }
        return false
    }

    fun sortListAccordingToDirection(
        list: List<TripExecutionDriver>,
        wayOrder: List<Int>
    ): List<TripExecutionDriver> {
        var tempList: MutableList<TripExecutionDriver> = list.toMutableList()
        for ((index, value) in wayOrder.withIndex()) {
            tempList[index] = list[value]
        }
        return tempList
    }

    fun checkGPSEnable(activity: Context) {
        val dialogBuilder = AlertDialog.Builder(activity)
        dialogBuilder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, _ ->
                dialog.dismiss()
                activity.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
        val alert = dialogBuilder.create()
        alert.show()
        alert.getButton(AlertDialog.BUTTON_NEGATIVE)
            .setBackgroundColor(activity.resources.getColor(com.ibex.fleetmanager.driver.R.color.white))
        alert.getButton(AlertDialog.BUTTON_NEGATIVE)
            .setTextColor(activity.resources.getColor(com.ibex.fleetmanager.driver.R.color.colorPrimary))
    }

    //Marker animation
    fun animateMarker(
        marker: Marker,
        toPosition: LatLng,
        mMap: GoogleMap,
        location: Location
    ) {
        val handler = Handler()
        val start: Long = SystemClock.uptimeMillis()
        val proj: Projection = mMap.projection
        val startPoint: Point = proj.toScreenLocation(marker.position)
        val startLatLng: LatLng = proj.fromScreenLocation(startPoint)
        val duration: Long = 500
        val interpolator: Interpolator = LinearInterpolator()
        handler.post(object : Runnable {
            override fun run() {
                val elapsed: Long = SystemClock.uptimeMillis() - start
                val t: Float = interpolator.getInterpolation(
                    elapsed.toFloat()
                            / duration
                )
                val lng = t * toPosition.longitude + (1 - t) * startLatLng.longitude
                val lat = t * toPosition.latitude + (1 - t) * startLatLng.latitude
                marker.position = LatLng(lat, lng)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    marker.rotation = location.bearingAccuracyDegrees
                } else {
                    marker.rotation = location.bearing
                }
                if (t < 1.0) {
                    // Post again 16ms later.
                    handler.postDelayed(this, 16)
                }
            }
        })
    }

    // move camera to that where is the location of the user
    fun moveCamera(latLng: LatLng, zoom: Float, mMap: GoogleMap) {
        val pakistan = CameraPosition.builder()
            .target(latLng)
            .zoom(zoom)
            .build()
        mMap.animateCamera(
            CameraUpdateFactory.newCameraPosition(pakistan),
            1000,
            null
        )
    }

    //Display Dialog Box on completion of trip
    fun displayDialogBox(
        calculateTimeDifference: String,
        activity: FragmentActivity,
        directionData: DirectionsDriverRouteResponse,
        locationList: List<LocalLocation>
    ) {
        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(activity)
        val dialogView: View = activity.layoutInflater.inflate(
            com.ibex.fleetmanager.driver.R.layout.dialog_complete_trip,
            null
        )
        dialogBuilder.setView(dialogView)
        val alertDialog: AlertDialog = dialogBuilder.create()
        //calculate distance from route
//        dialogView.findViewById<TextView>(com.ibex.fleetmanager.driver.R.id.time_elapsed_trip).text =
//            "$calculateTimeDifference and ${(directionData.distance.toDouble() / 1000.0).toOneDecimal()} km"
        //calculate distance from local Locations
        dialogView.findViewById<TextView>(com.ibex.fleetmanager.driver.R.id.time_elapsed_trip).text =
            "$calculateTimeDifference and ${(calculateDistance(locationList) / 1000.0).toOneDecimal()} km"
        dialogView.findViewById<ImageView>(com.ibex.fleetmanager.driver.R.id.cancel)
            .setOnClickListener {
                alertDialog.dismiss()
            }
        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        alertDialog.show()
    }

    fun calculateDistance(locationList: List<LocalLocation>): Float {
        var localDistance: Float = 0.0F
        for ((index, value) in locationList.withIndex()) {
            val location1 = Location("locationOne")
            val location2 = Location("locationTwo")
            if (index + 1 < locationList.size) {
                //first Location
                location1.altitude = value.latitude
                location1.longitude = value.longitude
                //Second Location
                location2.altitude = locationList[index + 1].latitude
                location2.longitude = locationList[index + 1].longitude
                //calculate distance
                localDistance += location1.distanceTo(location2)
            }
        }
        return localDistance
    }

    fun getLatLngList(locationList: List<LocalLocation>): MutableIterable<LatLng>? =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locationList.stream().map { location -> LatLng(location.latitude, location.longitude) }
                .collect(Collectors.toList())
        } else {
            val list: MutableList<LatLng> = ArrayList<LatLng>()
            locationList.forEach { list.add(LatLng(it.latitude, it.longitude)) }
            list
        }

    fun checkPermissions(activity: FragmentActivity) =
        PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
            activity, Manifest.permission.ACCESS_FINE_LOCATION
        ) && PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
            activity, Manifest.permission.READ_PHONE_STATE
        )

    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    private fun getBearing(begin: LatLng, end: LatLng): Float {
        val lat = Math.abs(begin.latitude - end.latitude)
        val lng = Math.abs(begin.longitude - end.longitude)
        if (begin.latitude < end.latitude && begin.longitude < end.longitude) return Math.toDegrees(Math.atan(lng / lat))
            .toFloat() else if (begin.latitude >= end.latitude && begin.longitude < end.longitude) return (90 - Math.toDegrees(
            Math.atan(lng / lat)
        ) + 90).toFloat() else if (begin.latitude >= end.latitude && begin.longitude >= end.longitude) return (Math.toDegrees(
            Math.atan(lng / lat)
        ) + 180).toFloat() else if (begin.latitude < end.latitude && begin.longitude >= end.longitude) return (90 - Math.toDegrees(
            Math.atan(lng / lat)
        ) + 270).toFloat()
        return (-1).toFloat()
    }

}