package com.ibex.fleetmanager.common.utility


import android.text.Editable
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.collections.ArrayList

fun String.isEmailValid(): Boolean {
    val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
    val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher: Matcher = pattern.matcher(this)
    return matcher.matches()
}

val Editable?.str: String
    get() {
        return this.toString()
    }

fun Double.toOneDecimal(): String {
    val df = DecimalFormat("#.#")
    df.roundingMode = RoundingMode.CEILING
    return df.format(this).toString()
}

//fun Double.toOneDecimal(): String {
//    val df = DecimalFormat("#.##")
//    df.roundingMode = RoundingMode.CEILING
//    return df.format(this).toString()
//}

fun String.isPhoneValid(): Boolean {
    var validateNumber: Boolean = false
    val list = arrayListOf<String>(
        "331",
        "332",
        "333",
        "334",
        "335",
        "336",
        "337",
        "338",
        "339",  //Ufone
        "300",
        "301",
        "302",
        "303",
        "304",
        "305",
        "306",
        "307",
        "308",
        "309",  //Mobilink
        "320",
        "321",
        "322",
        "323",
        "324",
        "325",
        "326",
        "327",
        "328",
        "329",  //Warid
        "340",
        "341",
        "342",
        "343",
        "344",
        "348",
        "345",
        "346",
        "347",
        "347",
        "348",
        "349", //Telenor
        "310",
        "311",
        "312",
        "313",
        "314",
        "315",
        "316",
        "317",
        "318",
        "319" //Zong
    )
    if (this.length == 11) {
        validateNumber = if (this[0].toString() == "0") {
            val code = this.subSequence(1, 4)
            list.contains(code)
        } else {
            false
        }
    } else if (this.length == 13) {
        validateNumber =
            if (this[0].toString() == "+" && this[1].toString() == "9" && this[2].toString() == "2") {
                list.contains(this.subSequence(3, 6))
            } else {
                false;
            }
    }
    return validateNumber
}

fun String.getDate(): String? {
    val dateFormat = SimpleDateFormat(
        "yyyy-MM-dd'T'HH:mm:ssZ",
        Locale.getDefault()
    )
    dateFormat.timeZone = TimeZone.getTimeZone("GMT")  // IMP !!!
    val givenDateString = this.trim()
    val sdf = SimpleDateFormat("yyyy-MM-ddTHH:mm:ssZ")
    val mDate = sdf.parse(givenDateString)
    val sdf2 = SimpleDateFormat("MMMM dd, yyyy", Locale.US)
    return sdf2.format(mDate).toString()
}

fun String.getTime(): String? {
    val dateFormat = SimpleDateFormat(
        "yyyy-MM-dd'T'HH:mm:ssZ",
        Locale.getDefault()
    )
    dateFormat.timeZone = TimeZone.getTimeZone("GMT")  // IMP !!!
    val givenDateString = this.trim()
    val sdf = SimpleDateFormat("yyyy-MM-ddTHH:mm:ssZ")
    val mDate = sdf.parse(givenDateString)
    val sdf2 = SimpleDateFormat("HH:mm", Locale.US)
    return sdf2.format(mDate).toString()
}