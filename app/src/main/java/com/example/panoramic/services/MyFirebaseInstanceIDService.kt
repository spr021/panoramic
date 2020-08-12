@file:Suppress("DEPRECATION")

package com.example.panoramic.services

import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class MyFirebaseInstanceIDService : FirebaseInstanceIdService() {
    override fun onTokenRefresh() {
        //Getting registration token
        val refreshedToken = FirebaseInstanceId.getInstance().token

    }

    private fun sendRegistrationToServer(token: String) {
        //You can implement this method to store the token on your server
        //Not required for current project
    }
}