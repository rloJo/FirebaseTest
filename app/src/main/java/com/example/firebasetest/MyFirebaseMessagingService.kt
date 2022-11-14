package com.example.firebasetest

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        // Get updated InstanceID token.
        Log.d(TAG, "Refreshed token: $token")

        // TODO: Implement this method to send any registration to your app's servers.
        // sendRegistrationToServer(token)
    }

    // foreground 에서만 호출 background일 경우 알림으로 호출
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "From: ${remoteMessage.from}")
        Log.d(TAG, "Message data payload: ${remoteMessage.data}")
        val msgBody = remoteMessage.notification?.body
        Log.d(TAG, "Message Notification Body: $msgBody")
        startActivity(
            Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                putExtra("message", msgBody)
            })
    }

    companion object {
        const val TAG = "MyFirebaseMessaging"
    }
}