package com.example.panoramic.app

import android.app.Activity
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.panoramic.R
import kotlinx.android.synthetic.main.toast_fragment_home.view.*


fun CustomToast(context: Activity, message: String, color: Int) {
    val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    val layout = layoutInflater.inflate(
        R.layout.toast_fragment_home,
        (context).findViewById(R.id.custom_toast_layout)
    )


    val drawable = ContextCompat.getDrawable(context, R.drawable.toast_background)
    drawable?.colorFilter = PorterDuffColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.MULTIPLY)
    layout.background = drawable

    layout.custom_toast_message.text = message

    val toast = Toast(context.applicationContext)
    toast.duration = Toast.LENGTH_LONG
    toast.setGravity(Gravity.FILL_HORIZONTAL or Gravity.TOP, 0, 0)
    toast.view = layout //setting the view of custom toast layout
    toast.show()
}