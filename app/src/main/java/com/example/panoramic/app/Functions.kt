package com.example.panoramic.app

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.app.Activity
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout
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

fun SlideView(view: View?, currentHeight: Int, newHeight: Int) {

    view?.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
    val finalHeight = view!!.measuredHeight
    val slideAnimator = ValueAnimator
        .ofInt(currentHeight, newHeight)
        .setDuration(500)

    /* We use an update listener which listens to each tick
     * and manually updates the height of the view  */
    slideAnimator.addUpdateListener { animation1: ValueAnimator ->
        val value = animation1.animatedValue as Int
        view!!.layoutParams.height = value
        view.requestLayout()
    }

    /*  We use an animationSet to play the animation  */
    val animationSet = AnimatorSet().apply {
        interpolator = AccelerateDecelerateInterpolator()
        play(slideAnimator)
        start()
    }
}


