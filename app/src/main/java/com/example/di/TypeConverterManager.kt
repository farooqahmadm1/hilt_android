package com.ibex.fleetmanager.common.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ibex.fleetmanager.common.model.UserResponse
import com.ibex.fleetmanager.common.network.driver.responses.*
import com.ibex.fleetmanager.common.network.driver.responses.Passenger
import com.ibex.fleetmanager.common.network.responses.RoutesItems
import com.ibex.fleetmanager.common.network.user.responses.*


class TypeConverterManager {

    private val gson = Gson()

    @TypeConverter
    fun fromUserResponse(list: List<UserResponse>): String? {
        return gson.toJson(list)
    }

    @TypeConverter
    fun stringToUserResponse(data: String): List<UserResponse>? {
        val type = object : TypeToken<List<UserResponse>>() {}.type
        return gson.fromJson(data, type)
    }


    @TypeConverter
    fun fromVehicleResponse(vehicle: Vehicle?): String? {
        if (vehicle == null)
            return null
        return gson.toJson(vehicle)
    }

    @TypeConverter
    fun stringToVehicle(data: String?): Vehicle? {
        if (data == null)
            return null
        val type = object : TypeToken<Vehicle>() {}.type
        return gson.fromJson(data, type)
    }

    /****
     * This is used to convert List<String> to json and backwards for room.
     */
    @TypeConverter
    fun fromStringList(stringList: List<String>?): String? {
        if (stringList == null)
            return null
        val gson = Gson()
        return gson.toJson(stringList)
    }

    @TypeConverter
    fun toStringList(stringJson: String?): List<String>? {
        if (stringJson == null)
            return null
        val type = object : TypeToken<List<String>>() {}.type
        val gson = Gson()
        return gson.fromJson<List<String>>(stringJson, type)
    }

    /****
     * This is used to convert Routes Object link[RoutesItems] to json and backwards for room.
     */
    @TypeConverter
    fun fromRoutesItems(routesItems: RoutesItems?): String? {
        if (routesItems == null) {
            return null
        }
        val gson = Gson()
        return gson.toJson(routesItems)
    }

    @TypeConverter
    fun toRoutesItems(stringJson: String?): RoutesItems? {
        if (stringJson == null)
            return null
        val type = object : TypeToken<RoutesItems>() {

        }.type
        val gson = Gson()
        return gson.fromJson<RoutesItems>(stringJson, type)
    }


    @TypeConverter
    fun fromProfileList(stringList: List<Profile>?): String? {
        if (stringList == null || stringList.isEmpty())
            return null
        val gson = Gson()
        return gson.toJson(stringList)
    }

    @TypeConverter
    fun toProfileList(stringJson: String?): List<Profile>? {
        if (stringJson == null)
            return null
        val gson = Gson()
        val type = object : TypeToken<List<Profile>>() {}.type
        return gson.fromJson<List<Profile>>(stringJson, type)
    }

    @TypeConverter
    fun fromTripExecutionDriverList(stringList: List<TripExecutionDriver>?): String? {
        if (stringList == null || stringList.isEmpty())
            return null
        val gson = Gson()
        return gson.toJson(stringList)
    }

    @TypeConverter
    fun toTripExecutionDriverList(stringJson: String?): List<TripExecutionDriver>? {
        if (stringJson == null)
            return null
        val gson = Gson()
        val type = object : TypeToken<List<TripExecutionDriver>>() {}.type
        return gson.fromJson<List<TripExecutionDriver>>(stringJson, type)
    }

    @TypeConverter
    fun fromTrip(string: Trip): String {
        val gson = Gson()
        return gson.toJson(string)
    }

    @TypeConverter
    fun toTrip(stringJson: String?): Trip? {
        if (stringJson == null) {
            return null
        }
        val type = object : TypeToken<Trip>() {}.type
        val gson = Gson()
        return gson.fromJson<Trip>(stringJson, type)
    }


    @TypeConverter
    fun fromRoute(string: Route?): String? {
        if (string == null)
            return null
        val gson = Gson()
        return gson.toJson(string)
    }

    @TypeConverter
    fun toRoute(stringJson: String?): Route? {
        if (stringJson == null)
            return null
        val type = object : TypeToken<Route>() {}.type
        val gson = Gson()
        return gson.fromJson<Route>(stringJson, type)
    }

    @TypeConverter
    fun fromPassenger(string: Passenger?): String? {
        if (string == null)
            return null
        val gson = Gson()
        return gson.toJson(string)
    }

    @TypeConverter
    fun toPassenger(stringJson: String?): Passenger? {
        if (stringJson == null)
            return null
        val type = object : TypeToken<Passenger>() {}.type
        val gson = Gson()
        return gson.fromJson<Passenger>(stringJson, type)
    }

    @TypeConverter
    fun fromTripData(string: TripData?): String? {
        if (string == null)
            return null
        val gson = Gson()
        return gson.toJson(string)
    }

    @TypeConverter
    fun toTripData(stringJson: String?): TripData? {
        if (stringJson == null)
            return null
        val type = object : TypeToken<TripData>() {}.type
        val gson = Gson()
        return gson.fromJson<TripData>(stringJson, type)
    }


    @TypeConverter
    fun fromPastTripData(string: PastTripData?): String? {
        if (string == null)
            return null
        val gson = Gson()
        return gson.toJson(string)
    }

    @TypeConverter
    fun toPastTripData(stringJson: String?): PastTripData? {
        if (stringJson == null)
            return null
        val type = object : TypeToken<PastTripData>() {}.type
        val gson = Gson()
        return gson.fromJson<PastTripData>(stringJson, type)
    }

    @TypeConverter
    fun fromPastRoute(string: PastRoute?): String? {
        if (string == null)
            return null
        val gson = Gson()
        return gson.toJson(string)
    }

    @TypeConverter
    fun toPastRoute(stringJson: String?): PastRoute? {
        if (stringJson == null)
            return null
        val type = object : TypeToken<PastRoute>() {}.type
        val gson = Gson()
        return gson.fromJson<PastRoute>(stringJson, type)
    }

    @TypeConverter
    fun fromPastTripExecutionDriver(string: List<PastTripExecutionDriver>?): String? {
        if (string == null)
            return null
        val gson = Gson()
        return gson.toJson(string)
    }

    @TypeConverter
    fun toPastTripExecutionDriver(stringJson: String?): List<PastTripExecutionDriver>? {
        if (stringJson == null)
            return null
        val type = object : TypeToken<List<PastTripExecutionDriver>>() {}.type
        val gson = Gson()
        return gson.fromJson<List<PastTripExecutionDriver>>(stringJson, type)
    }

    @TypeConverter
    fun fromViaWaypoint(string: List<ViaWaypoint>?): String? {
        if (string == null)
            return null
        val gson = Gson()
        return gson.toJson(string)
    }

    @TypeConverter
    fun toViaWaypoint(stringJson: String?): List<ViaWaypoint>? {
        if (stringJson == null)
            return null
        val type = object : TypeToken<List<ViaWaypoint>>() {}.type
        val gson = Gson()
        return gson.fromJson<List<ViaWaypoint>>(stringJson, type)
    }

    @TypeConverter
    fun fromSteps(string: List<Steps>?): String? {
        if (string == null)
            return null
        val gson = Gson()
        return gson.toJson(string)
    }

    @TypeConverter
    fun toSteps(stringJson: String?): List<Steps>? {
        if (stringJson == null)
            return null
        val type = object : TypeToken<List<Steps>>() {}.type
        val gson = Gson()
        return gson.fromJson<List<Steps>>(stringJson, type)
    }

    @TypeConverter
    fun fromPastPassenger(string: PastPassenger?): String? {
        if (string == null)
            return null
        val gson = Gson()
        return gson.toJson(string)
    }

    @TypeConverter
    fun toPastPassenger(stringJson: String?): PastPassenger? {
        if (stringJson == null)
            return null
        val type = object : TypeToken<PastPassenger>() {}.type
        val gson = Gson()
        return gson.fromJson<PastPassenger>(stringJson, type)
    }

    @TypeConverter
    fun fromDriverDetails(string: DriverDetails?): String? {
        if (string == null)
            return null
        val gson = Gson()
        return gson.toJson(string)
    }

    @TypeConverter
    fun toDriverDetails(stringJson: String?): DriverDetails? {
        if (stringJson == null)
            return null
        val type = object : TypeToken<DriverDetails>() {}.type
        val gson = Gson()
        return gson.fromJson<DriverDetails>(stringJson, type)
    }

    @TypeConverter
    fun fromIntList(string: List<Int>?): String? {
        if (string == null)
            return null
        val gson = Gson()
        return gson.toJson(string)
    }

    @TypeConverter
    fun toIntList(stringJson: String?): List<Int>? {
        if (stringJson == null)
            return null
        val type = object : TypeToken<List<Int>>() {}.type
        val gson = Gson()
        return gson.fromJson<List<Int>>(stringJson, type)
    }

    @TypeConverter
    fun fromLegs(string: List<Legs>?): String? {
        if (string == null)
            return null
        val gson = Gson()
        return gson.toJson(string)
    }

    @TypeConverter
    fun toLegs(stringJson: String?): List<Legs>? {
        if (stringJson == null)
            return null
        val type = object : TypeToken<List<Legs>>() {}.type
        val gson = Gson()
        return gson.fromJson<List<Legs>>(stringJson, type)
    }

    fun fromTripStart(obj: TripStart): String {
        val gson = Gson()
        return gson.toJson(obj)
    }

    fun toTripStart(stringJson: String): TripStart {
        val type = object : TypeToken<TripStart>() {}.type
        val gson = Gson()
        return gson.fromJson<TripStart>(stringJson, type)
    }

}