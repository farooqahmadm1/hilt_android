package com.ibex.fleetmanager.common.utility

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.text.format.DateFormat
import android.text.format.DateUtils
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ibex.fleetmanager.common.Constants
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.Reader
import java.lang.reflect.Type
import java.net.NetworkInterface
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin


object Utils {
    private var gson: Gson = GsonBuilder().disableHtmlEscaping().create()
    const val MAX_RETRIES = 1L
    const val LOCATION_MAX_RETRIES = 0L
    private const val INITIAL_BACKOFF = 2000L
    fun getBackoffDelay(attempt: Long) = INITIAL_BACKOFF * (attempt + 1)

    fun getFileMultiPart(file: File): MultipartBody.Part =
        MultipartBody.Part.createFormData(
            "file",
            file.name,
            file.asRequestBody("multipart/form-data".toMediaType())
        )

    @JvmStatic
    fun formatTimeString(dateTimeString: String?): String {
        return try {
            if (dateTimeString != null) {
                val rawSDF = getDateFormat(dateTimeString)
                val rawDate: Date = rawSDF.parse(dateTimeString)
                SimpleDateFormat("hh:mm a").format(rawDate)
            } else {
                Constants.EMPTY_STRING
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Constants.EMPTY_STRING
        }
    }

    @JvmStatic
    fun formatDateAndDayString(dateTimeString: String): String {
        return try {
            val rawSDF = getDateFormat(dateTimeString)
            val rawDate: Date = rawSDF.parse(dateTimeString)
            SimpleDateFormat("EEEE dd MMMM YYYY").format(rawDate)
        } catch (e: Exception) {
            e.printStackTrace()
            Constants.EMPTY_STRING
        }
    }

    @JvmStatic
    fun getFormatedDateOnly(dateTimeString: String): String {
        return try {
            val rawSDF = getDateFormat(dateTimeString)
            val rawDate: Date = rawSDF.parse(dateTimeString)
            SimpleDateFormat("dd-MM-yyyy").format(rawDate)
        } catch (e: Exception) {
            e.printStackTrace()
            Constants.EMPTY_STRING
        }
    }

    @JvmStatic
    fun getFormatedDateMonthOnly(dateTimeString: String): String {
        return try {
            val rawSDF = getDateFormat(dateTimeString)
            val rawDate: Date = rawSDF.parse(dateTimeString)
            SimpleDateFormat("dd-MMM").format(rawDate)
        } catch (e: Exception) {
            e.printStackTrace()
            Constants.EMPTY_STRING
        }
    }

    @JvmStatic
    fun getFormatedDayAndDate(dateTimeString: String): String {
        return try {
            val rawSDF = getDateFormat(dateTimeString)
            val rawDate: Date = rawSDF.parse(dateTimeString)
            SimpleDateFormat("EEEE dd MMMM YYYY").format(rawDate)
        } catch (e: Exception) {
            e.printStackTrace()
            Constants.EMPTY_STRING
        }
    }

    @JvmStatic
    fun getFormatedDayAndDateWithoutYear(dateTimeString: String): String {
        return try {
            val rawSDF = getDateFormat(dateTimeString)
            val rawDate: Date = rawSDF.parse(dateTimeString)
            SimpleDateFormat("EEEE dd MMMM").format(rawDate)
        } catch (e: Exception) {
            e.printStackTrace()
            Constants.EMPTY_STRING
        }
    }

    fun getDateTimeRaw(): Date = Calendar.getInstance().time

    fun getDateTimeRaw(dateTimeString: String): Date? {
        return try {
            val rawSDF = getDateFormat(dateTimeString)
            rawSDF.parse(dateTimeString)
        } catch (e: Exception) {
            e.printStackTrace()
            getDateTimeRaw()
        }
    }

    private fun getDateFormat(string: String): SimpleDateFormat {
        return when (string.length) {
            34 -> SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSZ")
            33 -> SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZ")
            32 -> SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ")
            31 -> SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSZ")
            30 -> SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSZ")
            29 -> SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            28 -> SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSZ")
            27 -> SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SZ")
            else -> SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        }
    }


    fun calculateTimeDifference(dateTimeString: String): String {
        val difference = getDateTimeRaw().time - getDateTimeRaw(dateTimeString)?.time!!
        var seconds = TimeUnit.MILLISECONDS.toSeconds(difference)
        if (seconds < 0) {
            seconds = -seconds
        }
        val day = TimeUnit.SECONDS.toDays(seconds).toInt()
        seconds = TimeUnit.SECONDS.toSeconds(seconds) - day * 86400
        if (!DateUtils.isToday(getDateTimeRaw(dateTimeString)?.time!!)) {
            seconds -= 86400
            if (seconds < 0) {
                seconds = -seconds
            }
        }
        val hours = TimeUnit.SECONDS.toHours(seconds)
        val minute = TimeUnit.SECONDS.toMinutes(seconds) - TimeUnit.SECONDS.toHours(seconds) * 60
        return if (hours.toInt() != 0) {
            "$hours Hour: $minute min"
        } else {
            "$minute Minutes"
        }
    }


    fun calculateTimeDifferenceInMinuteInt(dateTimeString: String): Long {
        val difference = getDateTimeRaw().time - getDateTimeRaw(dateTimeString)?.time!!
        var seconds = TimeUnit.MILLISECONDS.toSeconds(difference)
        if (seconds < 0) {
            seconds = -seconds
        }
        val day = TimeUnit.SECONDS.toDays(seconds).toInt()
        seconds = TimeUnit.SECONDS.toSeconds(seconds) - day * 86400
        if (!DateUtils.isToday(getDateTimeRaw(dateTimeString)?.time!!)) {
            seconds -= 86400
            if (seconds < 0) {
                seconds = -seconds
            }
        }
        return TimeUnit.SECONDS.toMinutes(seconds)
    }

    fun calculateTimeDifference(startTime: String, endTime: String): String {
        val difference = getDateTimeRaw(startTime)?.time!! - getDateTimeRaw(endTime)?.time!!
        var seconds = TimeUnit.MILLISECONDS.toSeconds(difference)
        if (seconds < 0) {
            seconds = -seconds
        }
        val day = TimeUnit.SECONDS.toDays(seconds).toInt()
        seconds = TimeUnit.SECONDS.toSeconds(seconds) - day * 86400
        val hours = TimeUnit.SECONDS.toHours(seconds)
        val minute = TimeUnit.SECONDS.toMinutes(seconds) - TimeUnit.SECONDS.toHours(seconds) * 60
        return when {
            day != 0 -> {
                "$day Day: $hours HR: $minute Min"
            }
            hours.toInt() != 0 -> {
                "$hours HR: $minute Min"
            }
            minute.toInt() != 0 -> {
                "$minute Min"
            }
            else -> {
                "$seconds Sec"
            }
        }
    }


    fun calculateTimeDifferenceInMinutes(dateTimeString: String = "2020-05-04T15:53:10+5:00"): Long {
        var difference = getDateTimeRaw().time - getDateTimeRaw(dateTimeString)?.time!!
        if (difference < 0) {
            difference = -difference
        }
        var seconds = TimeUnit.MILLISECONDS.toSeconds(difference)
        val day = TimeUnit.SECONDS.toDays(seconds).toInt()
        seconds = TimeUnit.SECONDS.toSeconds(seconds) - (day * 86400)
        if (!DateUtils.isToday(getDateTimeRaw(dateTimeString)?.time!!)) {
            seconds -= 86400
            if (seconds < 0) {
                seconds = -seconds
            }
        }
        return TimeUnit.SECONDS.toMinutes(seconds)
    }

    @JvmStatic
    fun getCurrentDateTime(): String =
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(getDateTimeRaw())

    //Dialogs
    @JvmStatic
    fun getCallIntent(phoneNum: String): Intent {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNum")
        return intent
    }

    @JvmStatic
    fun getCurrentDateTimeRaw(): String {
        val rawDate = Calendar.getInstance().time
        return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(rawDate)
    }

    fun getRawDate(dateTimeString: String): Date {
        return try {
            val rawSDF = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            rawSDF.parse(dateTimeString)
        } catch (e: Exception) {
            e.printStackTrace()
            getCurrentRawDate()
        }
    }

    private fun getCurrentRawDate(): Date = Calendar.getInstance().time

    @JvmStatic
    fun getTimeAgo(timeString: String): String {
        try {
            val rawSDF = getDateFormat(timeString)
            val past = rawSDF.parse(timeString)!!
            val now = Date()
            val seconds: Long = TimeUnit.MILLISECONDS.toSeconds(now.time - past.time)
            val minutes: Long = TimeUnit.MILLISECONDS.toMinutes(now.time - past.time)
            val hours: Long = TimeUnit.MILLISECONDS.toHours(now.time - past.time)
            val days: Long = TimeUnit.MILLISECONDS.toDays(now.time - past.time)
            return when {
                seconds < 60 -> {
                    "$seconds seconds ago"
                }
                minutes < 60 -> {
                    "$minutes minutes ago"
                }
                hours < 24 -> {
                    "$hours hours ago"
                }
                else -> {
                    "$days days ago"
                }
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            return "--"
        }
    }

    @JvmStatic
    fun getDuration(startTime: String, endTime: String): String {
        try {
            val rawSDF = getDateFormat(startTime)
            val rawSDF2 = getDateFormat(endTime)
            val start = rawSDF.parse(startTime)!!
            val end = rawSDF2.parse(endTime)!!
            val seconds: Long = TimeUnit.MILLISECONDS.toSeconds(end.time - start.time)
            val minutes: Long = TimeUnit.MILLISECONDS.toMinutes(end.time - start.time)
            val hours: Long = TimeUnit.MILLISECONDS.toHours(end.time - start.time)
            val days: Long = TimeUnit.MILLISECONDS.toDays(end.time - start.time)
            return when {
                seconds < 60 -> {
                    "$seconds Seconds"
                }
                minutes < 60 -> {
                    "$minutes Minutes "
                }
                hours < 24 -> {
                    "$hours Hour "
                }
                else -> {
                    "$days days"
                }
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            return "--"
        }
    }

    @JvmStatic
    fun distanceBetweenLocations(
        lat1: Double,
        lon1: Double,
        lat2: Double,
        lon2: Double
    ): Double {
        val theta = lon1 - lon2
        var dist = (sin(deg2rad(lat1))
                * sin(deg2rad(lat2))
                + (cos(deg2rad(lat1))
                * cos(deg2rad(lat2))
                * cos(deg2rad(theta))))
        dist = acos(dist)
        dist = rad2deg(dist)
        dist *= 60 * 1.1515
        return dist * 1000
    }

    private fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    private fun rad2deg(rad: Double): Double {
        return rad * 180.0 / Math.PI
    }

    fun getMacAddr(): String? {
        try {
            val all = Collections.list(NetworkInterface.getNetworkInterfaces())
            for (nif in all) {
                if (!nif.name.equals("wlan0", ignoreCase = true)) continue

                val macBytes = nif.hardwareAddress ?: return ""

                val res1 = StringBuilder()
                for (b in macBytes) {
                    //res1.append(Integer.toHexString(b & 0xFF) + ":");
                    res1.append(String.format("%02X:", b))
                }

                if (res1.isNotEmpty()) {
                    res1.deleteCharAt(res1.length - 1)
                }
                print(res1.toString())
                return res1.toString()
            }
        } catch (ex: Exception) {
            return null
        }
        return null

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

    fun <T> convertStringToJsonObject(body: Reader, type: Type): T {
        return gson.fromJson(body, type)
    }

    fun getTimestampDate(time: Long): String {
        try {
            val cal = Calendar.getInstance(Locale.ENGLISH)
            cal.timeInMillis = time * 1000
            val now = Date()
            val seconds: Long = TimeUnit.MILLISECONDS.toSeconds(now.time - cal.timeInMillis)
            val minutes: Long = TimeUnit.MILLISECONDS.toMinutes(now.time - cal.timeInMillis)
            val hours: Long = TimeUnit.MILLISECONDS.toHours(now.time - cal.timeInMillis)
            val days: Long = TimeUnit.MILLISECONDS.toDays(now.time - cal.timeInMillis)
            return when {
                seconds < 60 -> {
                    "just now"
                }
                minutes < 60 -> {
                    "$minutes min"
                }
                hours < 24 -> {
                    "$hours hours"
                }
                else -> {
                    "$days days"
                }
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            return "--"
        }
    }

    fun formateMiliSeconds(miliseconds: Long): String {
        // Create a DateFormatter object for displaying date in specified format.
        val formatter = SimpleDateFormat("m:ss");

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar = Calendar.getInstance();
        calendar.timeInMillis = miliseconds;
        return formatter.format(calendar.time);
    }

    fun getTimeStamp() {

    }
}