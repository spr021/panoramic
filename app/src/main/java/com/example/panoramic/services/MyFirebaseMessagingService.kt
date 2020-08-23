package com.example.panoramic.services

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.RingtoneManager
import android.net.Uri
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.panoramic.R
import com.example.panoramic.app.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.squareup.picasso.Picasso


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(FBtoken: String) {
        super.onNewToken(FBtoken)
        Log.i("DDDDDDDDD token", FBtoken)
        getSharedPreferences("_", Context.MODE_PRIVATE).edit().putString("fb", FBtoken)
            .apply()
    }



    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        val intent = Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        if (remoteMessage.data["action"] != null) {
            intent.putExtra("action",remoteMessage.data["action"])
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        val defaultSoundUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder: NotificationCompat.Builder = NotificationCompat.Builder(this, "1")
            .setSmallIcon(R.drawable.notification)
            .setContentTitle(remoteMessage.notification!!.title)
            .setContentText(remoteMessage.notification!!.body)
            .setAutoCancel(true)
            .setColor(ContextCompat.getColor(this, R.color.colorAccent))
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)
        try {
            val bmp: Bitmap? = Picasso.get().load(remoteMessage.notification!!.imageUrl).get()
            if(bmp != null) {
                notificationBuilder.setLargeIcon(bmp)
            }
        } catch (e: Throwable) {
        }

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())
    }

    fun getToken(context: Context): String? {
        return context.getSharedPreferences("_", Context.MODE_PRIVATE)
            .getString("fb", "empty")
    }
}