package com.example.panoramic.app.ui.forgetpassword.viewmodel

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.panoramic.app.MainActivity
import com.example.panoramic.remote.model.SendPhoneBody
import com.example.panoramic.remote.model.SendPhoneDto
import com.example.panoramic.remote.model.SendSmsBody
import com.example.panoramic.remote.model.SendSmsCodeDto
import com.example.panoramic.remote.service.SendPhoneService
import com.example.panoramic.remote.service.SendSmsCodeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class   ForgetpasswordSMSViewModel : ViewModel() {

    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    private val _requestResponse = MutableLiveData<Boolean>()
    val requestResponse: LiveData<Boolean>
        get() = _requestResponse

    private val _requestResponseResend = MutableLiveData<Boolean>()
    val requestResponseResend: LiveData<Boolean>
        get() = _requestResponseResend

    val timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

        override fun onTick(millisUntilFinished: Long) {
            _currentTime.value = millisUntilFinished / ONE_SECOND
        }

        override fun onFinish() {
            _currentTime.value = DONE
        }
    }

    // The String version of the current time
    val currentTimeString = Transformations.map(currentTime) { time ->
        DateUtils.formatElapsedTime(time)
    }

    fun onInputFillFinish(code: String, cookie: String?) {
        val body = SendSmsBody(cookie, code)
        val retrofit = Retrofit.Builder()
            .baseUrl(MainActivity.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(SendSmsCodeService::class.java)
        val call = service.sendSmsCode(body)
        call.enqueue(object : Callback<SendSmsCodeDto> {
            override fun onResponse(
                call: Call<SendSmsCodeDto>,
                response: Response<SendSmsCodeDto>
            ) {
                if (response.code() == 200) {
                    _requestResponse.value = response.body()!!.success
                }
            }

            override fun onFailure(call: Call<SendSmsCodeDto>, t: Throwable) {
                Log.i("SendPhoneDto", t.toString())
            }
        })
    }

    fun onResendCodeClick(cookie: String?, phone: String?) {
        val body = SendPhoneBody(cookie, phone)
        val retrofit = Retrofit.Builder()
            .baseUrl(MainActivity.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(SendPhoneService::class.java)
        val call = service.sendPhoneNumber(body)
        call.enqueue(object : Callback<SendPhoneDto> {
            override fun onResponse(
                call: Call<SendPhoneDto>,
                response: Response<SendPhoneDto>
            ) {
                if (response.code() == 200) {
                    _requestResponseResend.value = response.body()!!.message_sent
                }
            }

            override fun onFailure(call: Call<SendPhoneDto>, t: Throwable) {
                Log.i("SendPhoneDto", t.toString())
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

    companion object {
        // Time when the SMS is over
        private const val DONE = 0L

        // Countdown time interval
        private const val ONE_SECOND = 1000L

        // Total time for the SMS
        private const val COUNTDOWN_TIME = 120000L
    }
}