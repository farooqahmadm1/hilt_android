package com.ibex.fleetmanager.common.network.user.responses

import com.google.gson.annotations.SerializedName

data class Directions(
    @SerializedName("geocoded_waypoints") val geocodedWayPoints: List<GeocodedWaypoints>,
    @SerializedName("routes") val routes: List<Routes>,
    @SerializedName("status") val status: String
)

data class GeocodedWaypoints(

    @SerializedName("geocoder_status") val geocoderStatus: String,
    @SerializedName("place_id") val place_id: String,
    @SerializedName("types") val types: List<String>
)

data class Routes(
    @SerializedName("bounds") val bounds: Bounds,
    @SerializedName("copyrights") val copyrights: String,
    @SerializedName("legs") val legs: List<Legs>,
    @SerializedName("overview_polyline") val overviewPolyline: OverviewPolyline,
    @SerializedName("summary") val summary: String,
    @SerializedName("warnings") val warnings: List<String>,
    @SerializedName("waypoint_order") val wayPointOrder: List<Int>
)

data class OverviewPolyline(
    @SerializedName("points") val points: String
)

data class Bounds(
    @SerializedName("northeast") val northeast: Northeast,
    @SerializedName("southwest") val southwest: Southwest
)

data class Legs(
    @SerializedName("distance") val distance: Distance,
    @SerializedName("duration") val duration: Duration,
    @SerializedName("end_address") val endAddress: String,
    @SerializedName("end_location") val endLocation: EndLocation,
    @SerializedName("start_address") val startAddress: String,
    @SerializedName("start_location") val startLocation: StartLocation,
    @SerializedName("steps") val steps: List<Steps>,
    @SerializedName("traffic_speed_entry") val traffic_speed_entry: List<String>,
    @SerializedName("via_waypoint") val viaWaypoint: List<ViaWaypoint>
)

data class ViaWaypoint(
    @SerializedName("location") val location: Location,
    @SerializedName("step_index") val step_index: Int,
    @SerializedName("step_interpolation") val step_interpolation: Double
)

data class Location(
    @SerializedName("lat") val lat: Double,
    @SerializedName("lng") val lng: Double
)

data class Duration(

    @SerializedName("text") val text: String,
    @SerializedName("value") val value: Int
)

data class Distance(

    @SerializedName("text") val text: String,
    @SerializedName("value") val value: Int
)

data class EndLocation(

    @SerializedName("lat") val lat: Double,
    @SerializedName("lng") val lng: Double
)

data class StartLocation(

    @SerializedName("lat") val lat: Double,
    @SerializedName("lng") val lng: Double
)

data class Northeast(

    @SerializedName("lat") val lat: Double,
    @SerializedName("lng") val lng: Double
)

data class Polyline(

    @SerializedName("points") val points: String
)

data class Southwest(

    @SerializedName("lat") val lat: Double,
    @SerializedName("lng") val lng: Double
)

data class Steps(

    @SerializedName("distance") val distance: Distance,
    @SerializedName("duration") val duration: Duration,
    @SerializedName("end_location") val end_location: EndLocation,
    @SerializedName("html_instructions") val html_instructions: String,
    @SerializedName("polyline") val polyline: Polyline,
    @SerializedName("start_location") val start_location: StartLocation,
    @SerializedName("travel_mode") val travel_mode: String
)