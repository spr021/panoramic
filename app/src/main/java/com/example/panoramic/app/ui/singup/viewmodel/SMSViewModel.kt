package com.example.panoramic.app.ui.singup.viewmodel

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class SMSViewModel: ViewModel() {

    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    private val timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

        override fun onTick(millisUntilFinished: Long) {
            _currentTime.value = millisUntilFinished/ONE_SECOND
        }

        override fun onFinish() {
            _currentTime.value = DONE
            //onGameFinish()
        }
    }.start()

    // The String version of the current time
    val currentTimeString = Transformations.map(currentTime) { time ->
        DateUtils.formatElapsedTime(time)
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

    companion object{
        // Time when the SMS is over
        private const val DONE = 0L

        // Countdown time interval
        private const val ONE_SECOND = 1000L

        // Total time for the SMS
        private const val COUNTDOWN_TIME = 180000L
    }
}