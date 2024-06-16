package com.example.marvelapp.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebasePushService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e("TAG", "Token: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        NotificationHandler(this).sendNotification(message)
    }
}
