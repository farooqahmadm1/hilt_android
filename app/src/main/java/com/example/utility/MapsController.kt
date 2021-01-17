package com.ibex.fleetmanager.common.utility

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import android.view.View.MeasureSpec
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import com.google.maps.android.PolyUtil
import com.ibex.fleetmanager.common.R
import com.ibex.fleetmanager.common.databinding.PolyLineMarkerWindowsBinding
import com.ibex.fleetmanager.common.network.driver.responses.DirectionsDriverRouteResponse
import com.ibex.fleetmanager.common.network.driver.responses.TripExecutionDriver
import com.ibex.fleetmanager.common.network.driver.responses.TripStart
import com.ibex.fleetmanager.common.network.user.responses.DirectionsRouteResponse
import com.ibex.fleetmanager.common.network.user.responses.TripExecutionDataResult
import kotlin.random.Random


class MapsController(val context: Context, val googleMap: GoogleMap) {

    private var markerDrawables: Array<Int> = arrayOf(
        R.drawable.ic_passenger_marker_1,
        R.drawable.ic_passenger_marker_2,
        R.drawable.ic_passenger_marker_3
    )
    private val mContext: Context = context
    private val mGoogleMap: GoogleMap = googleMap

    private val mTimeSquare = LatLng(40.758895, -73.985131)

    private var mSpotMarkerList = ArrayList<Marker>()

    private var mRouteMarkerList = ArrayList<Marker>()
    private lateinit var mRoutePolyline: Polyline

    fun animateZoomInCamera() {
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mTimeSquare, 15f))
    }

    fun animateZoomOutCamera() {
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mTimeSquare, 10f))
    }

    fun drawRouteForPassenger(
        route: DirectionsRouteResponse,
        onGoingTripData: TripExecutionDataResult
    ) {
        clearMarkersAndRoute()
        // Add Starting location marker
        mRouteMarkerList.add(
            mGoogleMap.addMarker(
                MarkerOptions().position(LatLng(route.startLat!!, route.startLng!!))
                    .icon(
                        bitmapDescriptorFromVector(mContext, R.drawable.ic_marker_start_cap_user)
                    ).anchor(0.5f, 0.5f)
            )
        )

        // Add Starting marker info window
        mRouteMarkerList.add(
            mGoogleMap.addMarker(
                MarkerOptions()
                    .position(LatLng(route.startLat, route.startLng))
                    .icon(
                        BitmapDescriptorFactory.fromBitmap(
                            if (onGoingTripData.tripExecutionDrivers[0].status.equals(
                                    TripExecutionDriverStatus.PENDING
                                )
                            ) convertLayoutIntoBitmapForPassenger(
                                "",
                                "Meet you Driver here",
                                route.startName,
                                MarkerWindowsTypes.START_CAP.type
                            )
                            else convertLayoutIntoBitmapForPassenger(
                                "",
                                "Trip in progress",
                                "Your are picked up at ${Utils.formatTimeString(onGoingTripData.tripExecutionDrivers[0].pickupTime)}.",
                                MarkerWindowsTypes.START_CAP.type
                            )
                        )
                    )
                    .anchor(0.4f, 1.5f)
            )
        )
        // Add Ending location marker
        mRouteMarkerList.add(
            mGoogleMap.addMarker(
                MarkerOptions().position(LatLng(route.endLat!!, route.endLng!!))
                    .icon(bitmapDescriptorFromVector(mContext, R.drawable.ic_marker_end_cap_user))
                    .anchor(0.5f, 0.5f)
            )
        )
        // Add End marker info window
        mRouteMarkerList.add(
            mGoogleMap.addMarker(
                MarkerOptions()
                    .position(LatLng(route.endLat, route.endLng))
                    .icon(
                        BitmapDescriptorFactory.fromBitmap(
                            convertLayoutIntoBitmapForPassenger(
                                route.duration,
                                "Your Destination ",
                                route.endName,
                                MarkerWindowsTypes.END_CAP.type
                            )
                        )
                    )
                    .anchor(0.4f, 1.5f)
            )
        )

        // Draw a route polyline for the directions
        val polylineOptions = MyMapsFactory.drawRoute(mContext)
        val pointsList = PolyUtil.decode(route.overviewPolyline)
        for (point in pointsList) {
            polylineOptions.add(point)
        }

        mRoutePolyline = mGoogleMap.addPolyline(polylineOptions)

        mGoogleMap.animateCamera(MyMapsFactory.autoZoomLevel(mRouteMarkerList))
    }

    fun drawRouteForPassengerSignUp(route: DirectionsRouteResponse) {
        clearMarkersAndRoute()
        // Add Starting location marker
        mRouteMarkerList.add(
            mGoogleMap.addMarker(
                MarkerOptions().position(LatLng(route.startLat!!, route.startLng!!))
                    .icon(
                        bitmapDescriptorFromVector(mContext, R.drawable.ic_marker_start_cap_user)
                    ).anchor(0.5f, 0.5f)
            )
        )

        // Add Starting marker info window
        mRouteMarkerList.add(
            mGoogleMap.addMarker(
                MarkerOptions()
                    .position(LatLng(route.startLat, route.startLng))
                    .icon(
                        BitmapDescriptorFactory.fromBitmap(
                            convertLayoutIntoBitmapForPassenger(
                                "",
                                "Your pick up & drop off",
                                route.startName,
                                MarkerWindowsTypes.START_CAP.type
                            )
                        )
                    )
                    .anchor(0.4f, 1.5f)
            )
        )
        // Add Ending location marker
        mRouteMarkerList.add(
            mGoogleMap.addMarker(
                MarkerOptions().position(LatLng(route.endLat!!, route.endLng!!))
                    .icon(bitmapDescriptorFromVector(mContext, R.drawable.ic_marker_end_cap_user))
                    .anchor(0.5f, 0.5f)
            )
        )
        // Add End marker info window
        mRouteMarkerList.add(
            mGoogleMap.addMarker(
                MarkerOptions()
                    .position(LatLng(route.endLat, route.endLng))
                    .icon(
                        BitmapDescriptorFactory.fromBitmap(
                            convertLayoutIntoBitmapForPassenger(
                                route.duration,
                                "Your office ",
                                route.endName,
                                MarkerWindowsTypes.END_CAP.type
                            )
                        )
                    )
                    .anchor(0.4f, 1.5f)
            )
        )

        // Draw a route polyline for the directions
        val polylineOptions = MyMapsFactory.drawRoute(mContext)
        val pointsList = PolyUtil.decode(route.overviewPolyline)
        for (point in pointsList) {
            polylineOptions.add(point)
        }

        mRoutePolyline = mGoogleMap.addPolyline(polylineOptions)

        mGoogleMap.animateCamera(MyMapsFactory.autoZoomLevel(mRouteMarkerList))
    }

    fun drawRouteForDriver(
        route: DirectionsDriverRouteResponse,
        tripExecutionDrivers: List<TripExecutionDriver>?,
        type: Int = 1,
        onGoingTripData: TripStart
    ) {
        val startLatLng = if (onGoingTripData.trip.type == TripType.PICK_UP.value) {
            LatLng(
                onGoingTripData.trip._route!!.startingPointLat,
                onGoingTripData.trip._route!!.startingPointLong
            )
        } else {
            LatLng(
                onGoingTripData.trip._route!!.endingPointLat,
                onGoingTripData.trip._route!!.endingPointLong
            )
        }
        clearMarkersAndRoute()
        // Draw Start cap for poly line
        mRouteMarkerList.add(
            mGoogleMap.addMarker(
                MarkerOptions()
                    .position(LatLng(startLatLng.latitude, startLatLng.longitude))
                    .icon(
                        BitmapDescriptorFactory.fromResource(
                            R.drawable.ic_poly_start_cap_driver
                        )
                    )
                    .anchor(0.5f, 0.5f)
            )
        )

        // Draw Start cap Info window using marker
        mRouteMarkerList.add(
            mGoogleMap.addMarker(
                MarkerOptions()
                    .position(LatLng(startLatLng.latitude, startLatLng.longitude))
                    .icon(
                        BitmapDescriptorFactory.fromBitmap(
                            convertLayoutIntoBitmap(
                                Utils.formatTimeString(
                                    route.startTime
                                ),
                                "Begin Trip form here ",
                                route.startName,
                                MarkerWindowsTypes.START_CAP.type
                            )
                        )
                    )
                    .anchor(0.3f, 1.7f)
            )
        )
        // Draw End cap for poly line
        mRouteMarkerList.add(
            mGoogleMap.addMarker(
                MarkerOptions()
                    .position(LatLng(route.endLat!!, route.endLng!!))
                    .icon(
                        BitmapDescriptorFactory.fromResource(
                            R.drawable.ic_poly_end_cap_driver
                        )
                    ).anchor(0.5f, 0.5f)

            )
        )
        // Draw End cap Info window using marker
        mRouteMarkerList.add(
            mGoogleMap.addMarker(
                MarkerOptions()
                    .position(LatLng(route.endLat, route.endLng))
                    .icon(
                        BitmapDescriptorFactory.fromBitmap(
                            convertLayoutIntoBitmap(
                                secToTime(route.duration.toInt()),
                                "Destination ",
                                route.endName,
                                MarkerWindowsTypes.END_CAP.type
                            )
                        )
                    )
                    .anchor(0.3f, 1.7f)
            )
        )

        tripExecutionDrivers?.let {
            // Adding stops markers
            for (waypoint in tripExecutionDrivers) {
                mRouteMarkerList.add(
                    mGoogleMap.addMarker(
                        MarkerOptions()
                            .position(
                                LatLng(
                                    waypoint.passenger.pickupLat,
                                    waypoint.passenger.pickupLong
                                )
                            )
                            .icon(
                                BitmapDescriptorFactory.fromResource(R.drawable.ic_poly_stop_cap_driver)
                            ).anchor(0.5f, 0.5f)
                    )
                )
            }

            // Adding Info Windows for Passenger Stops
            addStopsMarkerAlwaysVisibleWindows(tripExecutionDrivers, route, type)

        }

        val polylineOptions = MyMapsFactory.drawDriverRoute(mContext)
        val pointsList = PolyUtil.decode(route.overviewPolyline)

        for (point in pointsList) {
            polylineOptions.add(point)
        }

        mRoutePolyline = mGoogleMap.addPolyline(polylineOptions)

        mGoogleMap.animateCamera(MyMapsFactory.autoZoomLevel(mRouteMarkerList))
    }

    private fun addStopsMarkerAlwaysVisibleWindows(
        tripExecutionDrivers: List<TripExecutionDriver>,
        route: DirectionsDriverRouteResponse,
        type: Int
    ) {

        tripExecutionDrivers.forEachIndexed { index, stops ->
            mRouteMarkerList.add(
                mGoogleMap.addMarker(
                    MarkerOptions()
                        .position(
                            LatLng(
                                stops.passenger.pickupLat,
                                stops.passenger.pickupLong
                            )
                        )
                        .icon(
                            BitmapDescriptorFactory.fromBitmap(
                                convertStopsLayoutIntoBitmap(
                                    name = stops.passenger.fullName,
                                    distance = calculatedistanceForPassenger(
                                        index, route, type
                                    ), time = calculateTimeForPassenger(index, route),
                                    userStatus = tripExecutionDrivers[index].status
                                )
                            )
                        )
                        .anchor(0.5f, 1.8f)
                )
            )
        }
    }

    private fun calculatedistanceForPassenger(
        indexOfPassengerObject: Int,
        route: DirectionsDriverRouteResponse,
        type: Int
    ): String {
        return try {
            var distanceSum = 0
            if (route.legs.isNotEmpty()) {
                if (route.waypointOrder.isNotEmpty()) {
                    distanceSum += route.legs[route.waypointOrder[indexOfPassengerObject]].distance.value
                }
            }
            if (type == 1) {
                "The Pickup is ${distanceSum / 1000} Km away."
            } else {
                "The Drop off is ${distanceSum / 1000} Km away."
            }
        } catch (e: Exception) {
            e.printStackTrace()
            "Distance not available."
        }
    }

    private fun calculateTimeForPassenger(
        indexOfPassengerObject: Int,
        route: DirectionsDriverRouteResponse
    ): String {
        return try {
            var timeSum = 0
            timeSum = route.legs[route.waypointOrder[indexOfPassengerObject]].duration.value
            secToTime(timeSum)
        } catch (e: Exception) {
            e.printStackTrace()
            "0"
        }
    }

    fun clearMarkersAndRoute() {
        for (marker in mRouteMarkerList) {
            marker.remove()
        }
        mRouteMarkerList.clear()

        if (::mRoutePolyline.isInitialized) {
            mRoutePolyline.remove()
        }
        mGoogleMap.clear()
    }

    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        return ContextCompat.getDrawable(context, vectorResId)?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap =
                Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }

    fun rand(from: Int, to: Int): Int {
        return Random.nextInt(to - from) + from
    }

    private fun convertStopsLayoutIntoBitmap(
        name: String,
        distance: String,
        time: String,
        userStatus: Int
    ): Bitmap {
        val view: PolyLineMarkerWindowsBinding = DataBindingUtil.inflate(
            (context as Activity).layoutInflater, R.layout.poly_line_marker_windows, null, false
        )
        view.tittle.text = name
        when (userStatus) {
            TripExecutionDriverStatus.PENDING.value -> {
                view.time.text = time
                view.message.text = distance
            }
            TripExecutionDriverStatus.CHECKIN.value -> {
                view.timeImage.visibility = View.GONE
                view.message.text = "Checked in"
            }
            TripExecutionDriverStatus.MISSED.value -> {
                view.timeImage.visibility = View.GONE
                view.message.text = "Missed "
            }
            TripExecutionDriverStatus.PASSENGERCANCELCONFIRMATION.value -> {
                view.timeImage.visibility = View.GONE
                view.message.text = "Passenger canceled drop off "
            }
            TripExecutionDriverStatus.PASSENGERCOMPLETECONFIRMATION.value -> {
                view.timeImage.visibility = View.GONE
                view.message.text = "Passenger accepted drop off "
            }
            else -> {
                view.timeImage.visibility = View.GONE
                view.message.text = ""
            }
        }
        view.rootView.measure(
            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        )
        view.rootView.layout(0, 0, view.rootView.measuredWidth, view.rootView.measuredHeight)

        view.rootView.isDrawingCacheEnabled = true
        view.rootView.buildDrawingCache()
        return view.rootView.drawingCache
    }

    private fun convertLayoutIntoBitmap(
        time: String,
        tiitle: String,
        message: String,
        type: Int
    ): Bitmap {
        val view: PolyLineMarkerWindowsBinding = DataBindingUtil.inflate(
            (context as Activity).layoutInflater, R.layout.poly_line_marker_windows, null, false
        )
        view.time.text = time
        view.tittle.text = tiitle
        view.message.text = message
        when (type) {
            MarkerWindowsTypes.START_CAP.type -> {
                view.timeImage.backgroundTintList =
                    ColorStateList.valueOf(context.resources.getColor(R.color.window_black))
            }
            MarkerWindowsTypes.END_CAP.type -> {
                view.timeImage.backgroundTintList =
                    ColorStateList.valueOf(context.resources.getColor(R.color.window_red))
            }
        }
        view.rootView.measure(
            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        )
        view.rootView.layout(0, 0, view.rootView.measuredWidth, view.rootView.measuredHeight)

        view.rootView.isDrawingCacheEnabled = true
        view.rootView.buildDrawingCache()
        return view.rootView.drawingCache
    }

    private fun convertLayoutIntoBitmapForPassenger(
        time: String,
        tiitle: String,
        message: String,
        type: Int
    ): Bitmap {
        val view: PolyLineMarkerWindowsBinding = DataBindingUtil.inflate(
            (context as Activity).layoutInflater, R.layout.poly_line_marker_windows, null, false
        )
        view.timeImage.backgroundTintList =
            ColorStateList.valueOf(context.resources.getColor(R.color.colorPrimaryDark))
        view.time.text = time
        view.tittle.text = tiitle
        view.message.text = message
        when (type) {
            MarkerWindowsTypes.START_CAP.type -> {
                view.timeImage.visibility = View.GONE
            }
            MarkerWindowsTypes.END_CAP.type -> {
            }
        }
        view.rootView.measure(
            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        )
        view.rootView.layout(0, 0, view.rootView.measuredWidth, view.rootView.measuredHeight)

        view.rootView.isDrawingCacheEnabled = true
        view.rootView.buildDrawingCache()
        return view.rootView.drawingCache
    }


    fun secToTime(sec: Int): String {
        val seconds = sec % 60
        var minutes = sec / 60
        if (minutes >= 60) {
            val hours = minutes / 60
            minutes %= 60
            if (hours >= 24) {
                val days = hours / 24
                return String.format(
                    "%d D %2d H%2d M",
                    days,
                    hours % 24,
                    minutes
                )
            }
            return String.format("%2d H%2d M", hours, minutes)
        }
        return String.format("%02d mins", minutes)
    }

}