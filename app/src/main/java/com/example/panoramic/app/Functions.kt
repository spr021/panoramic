package com.example.panoramic.app

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.app.Activity
import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.example.panoramic.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.toast_fragment_home.view.*


@Suppress("FunctionName")
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

@Suppress("FunctionName")
fun SlideView(view: View?, currentHeight: Int, newHeight: Int) {



    val slideAnimator = ValueAnimator
        .ofInt(currentHeight, newHeight)
        .setDuration(500)

    /* We use an update listener which listens to each tick
     * and manually updates the height of the view  */
    slideAnimator.addUpdateListener {
        val value = it.animatedValue as Int
        view!!.layoutParams.height = value
        view.requestLayout()
    }

    /*  We use an animationSet to play the animation  */
        AnimatorSet().apply {
        interpolator = AccelerateDecelerateInterpolator()
        play(slideAnimator)
        start()
    }
}

fun clearSharedPreferences(context: Activity, name: String){
    context.getSharedPreferences(name, Context.MODE_PRIVATE)!!.edit()
        .clear().apply()
}

@Suppress("DEPRECATION")
fun isOnline(context: Context?): Boolean {
    val connMgr = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
    return networkInfo?.isConnected == true
}

@BindingAdapter("imageUrl")
fun bindImage(view: ImageView, imageUrl: String?) {
    imageUrl.let {
        val imgUri = imageUrl?.toUri()?.buildUpon()?.scheme("http")?.build()
        Picasso.get()
            .load(imgUri)
            .error(R.drawable.home_person_icon)
            .placeholder(R.drawable.home_person_icon)
            .into(view)
    }
}


