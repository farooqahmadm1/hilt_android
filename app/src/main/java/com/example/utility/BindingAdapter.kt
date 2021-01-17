package com.ibex.fleetmanager.driver.utility

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentActivity
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.ibex.fleetmanager.common.BuildConfig
import com.ibex.fleetmanager.common.model.FirebaseMessage
import com.ibex.fleetmanager.common.network.driver.responses.PastTrip
import com.ibex.fleetmanager.common.network.driver.responses.TripExecutionDriver
import com.ibex.fleetmanager.common.utility.*
import com.ibex.fleetmanager.driver.DriverApp
import com.ibex.fleetmanager.driver.R
import de.hdodenhof.circleimageview.CircleImageView


object BindingAdapter {

    @JvmStatic
    @BindingAdapter("profileImage", "context")
    fun bindToProfileImage(imageView: CircleImageView, imageURL: String?, context: Context) {
        try {
            Glide.with(context)
                .load(BuildConfig.BASE_URL_MEDIA + imageURL)
                .apply(
                    RequestOptions()
                        .placeholder(getImagePlaceHolderLoader(context))
                        .error(R.drawable.ic_profile_metro_user)
                        .fallback(R.drawable.ic_profile_metro_user)
                )
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .signature(ObjectKey(imageURL ?: ""))
                .into(imageView)
        } catch (e: Exception) {
            Log.e("Glide Exception", "->", e)
        }
    }

    @JvmStatic
    @BindingAdapter("bind:imageURL", "bind:context")
    fun loadImage(imageView: CircleImageView, imageURL: String?, context: FragmentActivity?) {
        try {
            if (context != null) {
                Glide.with(context)
                    .load(BuildConfig.BASE_URL_MEDIA + imageURL)
                    .apply(
                        RequestOptions()
                            .placeholder(getImagePlaceHolderLoader(context))
                            .error(R.drawable.ic_profile_metro_user)
                            .fallback(R.drawable.ic_profile_metro_user)
                    )
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .signature(ObjectKey(imageURL ?: ""))
                    .into(imageView)
            }
        } catch (e: Exception) {
            Log.e("Glide Exception", "->", e)
        }
    }


    @JvmStatic
    @BindingAdapter("pranyofileImage", "context")
    fun bindToLicenseProfileImage(imageView: ImageView, imageURL: String?, context: Context) {
        try {
            Glide.with(context)
                .load(BuildConfig.BASE_URL_MEDIA + imageURL)
                .apply(
                    RequestOptions()
                        .placeholder(getImageSquarePlaceHolderLoader(context))
                        .error(R.drawable.ic_profile_add_placeholder)
                        .fallback(R.drawable.ic_profile_add_placeholder)
                )
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .signature(ObjectKey(imageURL ?: ""))
                .into(imageView)
        } catch (e: Exception) {
            Log.e("Glide Exception", "->", e)
        }
    }


    @JvmStatic
    fun getImageSquarePlaceHolderLoader(context: Context): CircularProgressDrawable {
        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 50f
        circularProgressDrawable.arrowEnabled = true
        circularProgressDrawable.setStartEndTrim(10f, 10f)
        circularProgressDrawable.setColorFilter(
            context.resources.getColor(
                R.color.colorPrimary
            ), PorterDuff.Mode.ADD
        )
        circularProgressDrawable.progressRotation = CircularProgressDrawable.LARGE.toFloat()
        circularProgressDrawable.setTint(context.resources.getColor(R.color.colorPrimary))
        circularProgressDrawable.start()
        return circularProgressDrawable
    }

    @JvmStatic
    fun getImagePlaceHolderLoader(context: Context): CircularProgressDrawable {
        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.arrowEnabled = true
        circularProgressDrawable.setStartEndTrim(10f, 10f)
        circularProgressDrawable.setColorFilter(
            context.resources.getColor(
                R.color.colorPrimary
            ), PorterDuff.Mode.ADD
        )
        circularProgressDrawable.progressRotation = CircularProgressDrawable.LARGE.toFloat()
        circularProgressDrawable.setTint(context.resources.getColor(R.color.colorPrimary))
        circularProgressDrawable.start()
        return circularProgressDrawable
    }


    @JvmStatic
    @BindingAdapter("scheduleDateTime")
    fun setScheduleDateTime(textView: TextView, data: String?) {
        textView.text = "${data?.let { Utils.formatDateAndDayString(it) }}"
    }


    @JvmStatic
    @BindingAdapter("scheduleTime")
    fun setScheduleTime(textView: TextView, data: String?) {
        textView.text = "${data?.let { Utils.formatTimeString(it) }}"
    }

    @JvmStatic
    @BindingAdapter("bind:checkStatus", "bind:context")
    fun setCheckStatus(btn: Button, data: TripExecutionDriver, context: FragmentActivity) {
        when (data.status) {
            BoardStatus.PENDING.value -> {
                btn.visibility = View.VISIBLE
                btn.isEnabled = true
            }
            BoardStatus.CHECK_IN.value -> {
                btn.visibility = View.VISIBLE
                btn.isEnabled = true
                btn.setBackgroundColor(context.resources.getColor(R.color.text_color_green))
                btn.setTextColor(context.resources.getColor(R.color.white))
            }
            BoardStatus.MISSED.value -> {
                btn.visibility = View.VISIBLE
                btn.isEnabled = false
                btn.setBackgroundColor(context.resources.getColor(R.color.text_color_grey))
                btn.setTextColor(context.resources.getColor(R.color.white))
            }
            BoardStatus.DROPPED.value -> {
                btn.visibility = View.VISIBLE
            }
            BoardStatus.DESTINATION_ARRIVED.value -> {
                btn.visibility = View.GONE
            }
            BoardStatus.PASSENGER_CANCEL.value -> {
                btn.visibility = View.GONE
            }
            BoardStatus.PASSENGER_COMPLETE_CONFIRMATION.value -> {
                btn.visibility = View.GONE
            }
            BoardStatus.PASSENGER_CANCEL_CONFIRMATION.value -> {
                btn.visibility = View.GONE
            }
        }
    }

    @JvmStatic
    @BindingAdapter("bind:missedStatus", "bind:context")
    fun setMissedStatus(btn: Button, data: TripExecutionDriver, context: FragmentActivity) {
        when (data.status) {
            BoardStatus.PENDING.value -> {
                btn.visibility = View.VISIBLE
                btn.isEnabled = true
            }
            BoardStatus.CHECK_IN.value -> {
                btn.visibility = View.VISIBLE
                btn.isEnabled = false
                btn.setBackgroundColor(context.resources.getColor(R.color.text_color_grey))
                btn.setTextColor(context.resources.getColor(R.color.white))
            }
            BoardStatus.MISSED.value -> {
                btn.visibility = View.VISIBLE
                btn.isEnabled = true
                btn.setBackgroundColor(context.resources.getColor(R.color.text_color_green))
                btn.setTextColor(context.resources.getColor(R.color.white))
            }
            BoardStatus.DROPPED.value -> {
                btn.visibility = View.GONE
            }
            BoardStatus.DESTINATION_ARRIVED.value -> {
                btn.visibility = View.GONE
            }
            BoardStatus.PASSENGER_CANCEL.value -> {
                btn.visibility = View.GONE
            }
            BoardStatus.PASSENGER_COMPLETE_CONFIRMATION.value -> {
                btn.visibility = View.GONE
            }
            BoardStatus.PASSENGER_CANCEL_CONFIRMATION.value -> {
                btn.visibility = View.GONE
            }
        }
    }


    @JvmStatic
    @BindingAdapter("setTripType")
    fun setTripType(textView: TextView, type: Int) {
        when (type) {
            1 -> textView.text = "Pickup"
            2 -> textView.text = "DropOff"
            else -> textView.text = ""
        }
    }

    @JvmStatic
    @BindingAdapter("setRemainingTime")
    fun setRemainingTime(textView: TextView, date: String?) {
        textView.text = "${date?.let { Utils.calculateTimeDifference(it) }}"
    }

    @JvmStatic
    @BindingAdapter("startTime", "endTime")
    fun setRemainingTime(textView: TextView, startTime: String?, endTime: String?) {
        startTime?.let {
            if (endTime != null) {
                textView.text = Utils.calculateTimeDifference(it, endTime)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("setChatTime")
    fun setChatTime(textView: TextView, time: Long) {
        textView.text = "${Utils.getTimestampDate(time)}"
    }

    @JvmStatic
    @BindingAdapter("setDistance")
    fun setDistance(textView: TextView, meters: Long) {
        val double = meters.toDouble() / 1000.0
        textView.text = "${double.toOneDecimal()} KM"
    }


    @JvmStatic
    @BindingAdapter("setStartBtnEnabled")
    fun setStartBtnEnabled(btn: Button, date: String?) {
        val value = date?.let { Utils.calculateTimeDifferenceInMinutes(it) }?.toInt()
        btn.isEnabled = value in 0..30
    }

    @JvmStatic
    @BindingAdapter("setBoardStatus")
    fun setBoardTime(btn: TextView, status: Int) {
        when (status) {
            BoardStatus.PENDING.value -> {
                btn.visibility = View.GONE
            }
            BoardStatus.CHECK_IN.value -> {
                btn.visibility = View.GONE
            }
            BoardStatus.MISSED.value -> {
                btn.visibility = View.GONE
            }
            BoardStatus.DROPPED.value -> {
                btn.visibility = View.VISIBLE
                btn.text = "Dropped"
            }
            BoardStatus.DESTINATION_ARRIVED.value -> {
                btn.visibility = View.VISIBLE
                btn.text = "Destination Arrived"
            }
            BoardStatus.PASSENGER_CANCEL.value -> {
                btn.visibility = View.VISIBLE
                btn.text = "Canceled"
            }
            BoardStatus.PASSENGER_COMPLETE_CONFIRMATION.value -> {
                btn.visibility = View.VISIBLE
                btn.text = "Completed"
            }
            BoardStatus.PASSENGER_CANCEL_CONFIRMATION.value -> {
                btn.visibility = View.VISIBLE
                btn.text = "Canceled"
            }
        }
    }


    @JvmStatic
    @BindingAdapter("setTripStatus")
    fun setTripStatus(textView: TextView, status: Int) {
        when (status) {
            TripStatus.IN_PROGRESS.value -> textView.text = "In Progress"
            TripStatus.COMPLETED.value -> {
                textView.text = "Completed"
                textView.setBackgroundColor(Color.parseColor("#0ba869"))
            }
            TripStatus.CANCELED.value -> {
                textView.text = "Canceled"
                textView.setBackgroundColor(Color.parseColor("#eb0045"))
            }
            else -> textView.text = ""
        }
    }

    @JvmStatic
    @BindingAdapter("setTripStatusAndColor")
    fun setTripStatusAndColor(textView: TextView, status: Int) {
        when (status) {
            TripStatus.IN_PROGRESS.value -> {
                textView.text = "In Progress"
                textView.setTextColor(Color.parseColor("#0ba869"))
            }
            TripStatus.COMPLETED.value -> {
                textView.text = "Completed"
                textView.setTextColor(Color.parseColor("#0ba869"))
            }
            TripStatus.CANCELED.value -> {
                textView.text = "Canceled"
                textView.setTextColor(Color.parseColor("#eb0045"))
            }
            else -> textView.text = ""
        }
    }

    @JvmStatic
    @BindingAdapter("setPassengerStatus")
    fun setPassengerStatus(txt: TextView, status: Int) {
        when (status) {
            BoardStatus.PENDING.value -> txt.text = "Pending"
            BoardStatus.CHECK_IN.value -> txt.text = "Check In"
            BoardStatus.MISSED.value -> {
                txt.text = "Missed"
                txt.setTextColor(Color.parseColor("#eb0045"))
            }
            BoardStatus.DROPPED.value -> {
                txt.text = "Dropped"
                txt.setTextColor(Color.parseColor("#0ba869"))
            }
            BoardStatus.DESTINATION_ARRIVED.value -> txt.text = "Destination Arrived"
            BoardStatus.PASSENGER_CANCEL.value -> {
                txt.text = "Canceled"
                txt.setTextColor(Color.parseColor("#eb0045"))
            }
            BoardStatus.PASSENGER_COMPLETE_CONFIRMATION.value -> {
                txt.text = "Completed"
                txt.setTextColor(Color.parseColor("#0ba869"))
            }
            BoardStatus.PASSENGER_CANCEL_CONFIRMATION.value -> {
                txt.text = "Canceled"
                txt.setTextColor(Color.parseColor("#eb0045"))
            }
            BoardStatus.DRIVER_CANCELED.value -> {
                txt.text = "Missed"
                txt.setTextColor(Color.parseColor("#eb0045"))
            }
        }
    }

    @JvmStatic
    @BindingAdapter("bind:pastDetailsImage", "bind:context")
    fun setPastScheduleMapImage(
        imageView: ImageView,
        data: PastTrip?,
        context: Context?
    ) {
        data?.let {
            val stringBuilder = StringBuilder()
            stringBuilder.append("http://maps.google.com/maps/api/staticmap?")
                // zoom and size for image
                .append("zoom=12&size=400x400")
                // Adding starting marker
                .append("&markers=icon:${BuildConfig.BASE_URL_MEDIA}media/group836/Group838.png|${it.trip!!.route!!.startingPointLat},${it.trip!!.route!!.startingPointLong}")
                // Adding ending marker
                .append("&markers=icon:${BuildConfig.BASE_URL_MEDIA}media/group836/Group838.png|${it.trip!!.route!!.endingPointLat},${it.trip!!.route!!.endingPointLong}")
            // adding the passenger markers
            it.tripExecutionDrivers!!.forEachIndexed { _, element ->
                stringBuilder.append("&markers=icon:${BuildConfig.BASE_URL_MEDIA}media/group836/Group838.png|${element.passenger!!.pickupLat},${element.passenger!!.pickupLong}")
            }
            // Adding the route poly line
            stringBuilder.append("&path=color:0xF82B47|weight:5|enc:${it.directionPolyline}")
                .append("&key=${context!!.resources.getString(R.string.google_maps_key)}")

            try {
                Glide.with(context)
                    .load(stringBuilder.toString())
                    .apply(
                        RequestOptions()
                            .placeholder(getImagePlaceHolderLoader(context))
                            .error(R.drawable.ic_past_trip)
                            .fallback(R.drawable.ic_past_trip)
                    )
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .signature(ObjectKey(stringBuilder.toString() ?: ""))
                    .into(imageView)
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }

    }

    @JvmStatic
    @BindingAdapter("setVoiceActionButtons")
    fun setVoiceActionButtons(imageView: ImageView, data: FirebaseMessage?) {
        data?.let {
            if (!data.voice.isNullOrEmpty()) {
                if (data.isUploadedSuccessfully!!) {
                    if (FileUtils.checkIfVoiceFileExits(data, DriverApp.applicationContext())) {
                        imageView.setImageDrawable(
                            ResourcesCompat.getDrawable(
                                DriverApp.applicationContext().resources,
                                R.drawable.ic_chat_play,
                                DriverApp.applicationContext().theme
                            )
                        )
                    } else {
                        imageView.setImageDrawable(
                            ResourcesCompat.getDrawable(
                                DriverApp.applicationContext().resources,
                                R.drawable.ic_chat_voice_download,
                                DriverApp.applicationContext().theme
                            )
                        )
                    }
                } else {
                    imageView.setImageDrawable(
                        ResourcesCompat.getDrawable(
                            DriverApp.applicationContext().resources,
                            R.drawable.ic_chat_voice_upload,
                            DriverApp.applicationContext().theme
                        )
                    )
                }
            }
        }
    }

    @JvmStatic
    @BindingAdapter("setImageSeen")
    fun setImageSeenDrawable(imageView: ImageView, seen: Boolean) {
        if (seen) {
            imageView.setImageDrawable(
                ResourcesCompat.getDrawable(
                    DriverApp.applicationContext().resources,
                    R.drawable.ic_chat_seen,
                    DriverApp.applicationContext().theme
                )
            )
        } else {
            imageView.setImageDrawable(
                ResourcesCompat.getDrawable(
                    DriverApp.applicationContext().resources,
                    R.drawable.ic_chat_unseen,
                    DriverApp.applicationContext().theme
                )
            )
        }
    }


}