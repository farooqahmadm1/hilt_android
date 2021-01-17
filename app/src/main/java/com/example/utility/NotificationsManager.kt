package com.ibex.fleetmanager.common.utility

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavDeepLinkBuilder
import com.ibex.fleetmanager.common.Constants
import com.ibex.fleetmanager.common.R

class NotificationsManager(val context: Context) {

    private var notificationId: Int = 888

    init {
        createNotificationChannel(
            "Fleet Manager",
            "Fleet Manager",
            "Fleet Manager"
        )
    }

    fun <T : Any?> generateGeneralNotification(
        tittle: String,
        body: String,
        channelID: String,
        channelDescription: String,
        fcmFor: Int,
        clickedDestination: Int,
        activity: Class<T>
    ) {
        val builder = NotificationCompat.Builder(context, channelID)
            .setSmallIcon(R.drawable.ic_marker_start_cap_user)
            .setContentTitle(tittle)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    context.resources,
                    R.drawable.ic_marker_start_cap_user
                )
            )
            .setContentText(body)
            .setAutoCancel(true)
            .setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(
                createPendingIntent(
                    body,
                    tittle,
                    clickedDestination,
                    activity,
                    fcmFor
                )
            )

        createNotificationChannel(channelID, channelID, channelDescription)
        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(notificationId /* ID of notification */, builder.build())
        }
        notificationId++
    }

    fun <T : Any?> generateGeneralLongTextNotification(
        tittle: String,
        bodySingleLine: String,
        bodyMultiLine: String,
        channelID: String,
        channelDescription: String,
        fcmFor: Int,
        clickedDestination: Int,
        activity: Class<T>,
        pendingIntent: PendingIntent? = null
    ) {

//        var intent = pendingIntent
//        if (intent == null){
//            intent = createPendingIntent(
//                bodySingleLine,
//                tittle,
//                clickedDestination,
//                activity,
//                fcmFor
//            )
//        }
        val builder = NotificationCompat.Builder(context, channelID)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    context.resources,
                    R.drawable.ic_marker_start_cap_user
                )
            )
            .setSmallIcon(R.drawable.ic_marker_start_cap_user)
            .setContentTitle(tittle)
            .setContentText(bodySingleLine)
            .setStyle(NotificationCompat.BigTextStyle().bigText(bodyMultiLine))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setNumber(notificationId)
            .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
            .setCategory(NotificationCompat.CATEGORY_STATUS)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setContentIntent(pendingIntent)

        createNotificationChannel(channelID, channelID, channelDescription)
        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(notificationId, builder.build())
        }
        notificationId++
    }

    private fun <T : Any?> createPendingIntent(
        body: String,
        tittle: String,
        clickedDestination: Int,
        activity: Class<T>,
        fcmFor: Int
    ): PendingIntent? {
        // Create an implicit intent for an Activity in your app

        val intent =
            Intent(context, activity).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                putExtra(Constants.NOTIFICATIONCLICKED_DESTINATION, clickedDestination)
                putExtra(Constants.FCM_BODY, body)
                putExtra(Constants.FCM_tittle, tittle)
                putExtra(Constants.FCM_FOR, fcmFor)
            }
        return PendingIntent.getActivity(context, 0, intent, 0)
    }

    /**
     * This Fundction is shared with in the members of this class
     * -- Responsible for the creating the channels for Android 7+
     */
    private fun createNotificationChannel(
        channelName: String,
        channelID: String,
        channelDescription: String
    ) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channelID, channelName, importance).apply {
                description = channelDescription
                setShowBadge(true)
                canShowBadge()
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context
                    .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}