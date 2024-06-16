package com.example.marvelapp.service

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.example.marvelapp.R
import com.example.marvelapp.presentation.MainActivity
import com.example.marvelapp.utils.NotificationConstants
import com.google.firebase.messaging.RemoteMessage

class NotificationHandler(private val service: FirebasePushService) {

    init {
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                NotificationConstants.CHANNEL_ID,
                NotificationConstants.CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply { description = NotificationConstants.CHANNEL_DESCRIPTION }

            with(NotificationManagerCompat.from(service)) {
                createNotificationChannel(notificationChannel)
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun sendNotification(remoteMessage: RemoteMessage) {

        val id = remoteMessage.data["id"]
        val uri = "${NotificationConstants.URI}/$id".toUri()
        val title = remoteMessage.notification?.title
        val text = remoteMessage.notification?.body

        val intent = Intent(
            Intent.ACTION_VIEW,
            uri,
            service,
            MainActivity::class.java
        )

        val pendingIntent: PendingIntent? = TaskStackBuilder.create(service).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(
                NotificationConstants.REQUEST_CODE,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
        }

        val builder =
            NotificationCompat.Builder(service, NotificationConstants.CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

        with(NotificationManagerCompat.from(service)) {
            notify(NotificationConstants.NOTIFICATION_ID, builder.build())
        }
    }
}
