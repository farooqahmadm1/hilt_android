package com.ibex.fleetmanager.common.network.user.requestBody

data class DirectionsRequestBody(
    val origin: String,
    val destination: String,
    val key: String
)