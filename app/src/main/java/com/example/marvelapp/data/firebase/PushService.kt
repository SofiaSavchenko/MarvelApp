package com.example.marvelapp.data.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e("TAG", "Token: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {

        message.notification?.title?.let { Log.d("TAG", "Title: $it") }
        message.notification?.body?.let { Log.d("TAG", "Body: $it") }
    }

    override fun handleIntent(intent: Intent) {
        try {
            val extras = intent.extras
            if (extras != null) {
                val builder = RemoteMessage.Builder("MessagingService")
                extras.keySet()?.forEach { key ->
                    extras.getString(key)?.let { value ->
                        builder.addData(key, value)
                    }
                }
                onMessageReceived(builder.build())
            } else {
                super.handleIntent(intent)
            }
        } catch (e: Exception) {
            super.handleIntent(intent)
        }
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "marvel"
            val channelName = "marvel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channelDescription = "marvel"
            val notificationChannel = NotificationChannel(
                channelId,
                channelName,
                importance
            ).apply {
                description = channelDescription
            }

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
}
