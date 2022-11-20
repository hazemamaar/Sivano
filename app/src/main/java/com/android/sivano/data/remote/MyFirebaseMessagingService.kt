package com.android.sivano.data.remote

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

class MyFirebaseMessagingService :  FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)

        sendTokenToRegisterOnServer(token)
    }
    private fun sendTokenToRegisterOnServer(token: String) {
        Log.e("tokkken", "onNewToken: "+token )
    }
}
