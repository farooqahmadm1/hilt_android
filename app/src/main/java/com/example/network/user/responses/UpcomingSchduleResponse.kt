package com.ibex.fleetmanager.common.network.user.responses

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.ibex.fleetmanager.common.base.ErrorResponse
import com.ibex.fleetmanager.common.network.responses.RoutesItems
import com.ibex.fleetmanager.common.network.responses.Vehicle

data class UpcomingSchduleResponse(
    @SerializedName("result") val result: UpcomingScheduleListResult?,
    @SerializedName("targetUrl") val targetUrl: String,
    @SerializedName("success") val _success: Boolean?,
    @SerializedName("error") val error: ErrorResponse?,
    @SerializedName("unAuthorizedRequest") val unAuthorizedRequest: Boolean,
    @SerializedName("__abp") val __abp: Boolean?
) {
    val success
        get() = _success
            ?: throw IllegalArgumentException("Success value is required.Found Null!")
}

data class UpcomingScheduleListResult(

    @SerializedName("totalCount") val totalCount: Int,
    @SerializedName("items") val items: List<UpcomingScheduleItems>
)

@Entity(tableName = "UpcomingTrips")
data class UpcomingScheduleItems(
    @PrimaryKey
    @SerializedName("scheduleId") val scheduleId: Int,
    @SerializedName("tripDate") val tripDate: String,
    @SerializedName("tripTime") val tripTime: String,
    @SerializedName("name") val name: String,
    @SerializedName("routeId") val routeId: Int,
    @SerializedName("trip") val trip: TripData?,
    @SerializedName("driver") val driver: DriverDetails?,
    @SerializedName("status") var status: String,
    @SerializedName("serviceType") val serviceType: Int,
    var viewType: Int = 1
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readParcelable(TripData::class.java.classLoader),
        parcel.readParcelable(DriverDetails::class.java.classLoader),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    constructor(viewTypeParam: Int) : this(
        trip = null,
        driver = null,
        tripDate = "",
        tripTime = "",
        scheduleId = 0,
        name = "",
        routeId = 0,
        status = "",
        serviceType = 0,
        viewType = viewTypeParam
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(scheduleId)
        parcel.writeString(tripDate)
        parcel.writeString(tripTime)
        parcel.writeString(name)
        parcel.writeInt(routeId)
        parcel.writeParcelable(trip, flags)
        parcel.writeParcelable(driver, flags)
        parcel.writeString(status)
        parcel.writeInt(serviceType)
        parcel.writeInt(viewType)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UpcomingScheduleItems> {
        override fun createFromParcel(parcel: Parcel): UpcomingScheduleItems {
            return UpcomingScheduleItems(parcel)
        }

        override fun newArray(size: Int): Array<UpcomingScheduleItems?> {
            return arrayOfNulls(size)
        }
    }
}

data class TripData(
    @SerializedName("creationTime") val creationTime: String,
    @SerializedName("name") val name: String,
    @SerializedName("startTime") val startTime: String,
    @SerializedName("tripStartAddress") val tripStartAddress: String,
    @SerializedName("tripEndAddress") val tripEndAddress: String,
    @SerializedName("route") val route: RoutesItems,
    @SerializedName("routeId") val routeId: Int,
    @SerializedName("passengerCount") val passengerCount: Int,
    @SerializedName("driverName") val driverName: String,
    @SerializedName("driverSurname") val driverSurname: String,
    @SerializedName("vehicle") val vehicle: Vehicle,
    @SerializedName("driverId") val driverId: Int,
    @SerializedName("isActive") val isActive: Boolean,
    @SerializedName("type") val type: Int,
    @SerializedName("passengers") val passengers: String,
    @SerializedName("id") val id: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readParcelable(RoutesItems::class.java.classLoader)!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readParcelable(Vehicle::class.java.classLoader)!!,
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(creationTime)
        parcel.writeString(name)
        parcel.writeString(startTime)
        parcel.writeString(tripStartAddress)
        parcel.writeString(tripEndAddress)
        parcel.writeParcelable(route, flags)
        parcel.writeInt(routeId)
        parcel.writeInt(passengerCount)
        parcel.writeString(driverName)
        parcel.writeString(driverSurname)
        parcel.writeParcelable(vehicle, flags)
        parcel.writeInt(driverId)
        parcel.writeByte(if (isActive) 1 else 0)
        parcel.writeInt(type)
        parcel.writeString(passengers)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TripData> {
        override fun createFromParcel(parcel: Parcel): TripData {
            return TripData(
                parcel
            )
        }

        override fun newArray(size: Int): Array<TripData?> {
            return arrayOfNulls(size)
        }
    }


}
