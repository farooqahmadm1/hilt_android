package com.ibex.fleetmanager.common.network.driver.requestBody

data class DirectionsRequestBody(
    val mode: String,
    val origin: String,
    val destination: String,
    val waypoints: String,
    val key: String
)