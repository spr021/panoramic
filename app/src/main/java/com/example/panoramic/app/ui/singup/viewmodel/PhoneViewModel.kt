package com.example.panoramic.app.ui.singup.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.panoramic.app.MainActivity
import com.example.panoramic.remote.model.SendPhoneBody
import com.example.panoramic.remote.model.SendPhoneDto
import com.example.panoramic.remote.service.SendPhoneService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PhoneViewModel() : ViewModel() {

    private val _requestResponse = MutableLiveData<Boolean>()
    val requestResponse: LiveData<Boolean>
        get() = _requestResponse

    fun sendPhoneNumber(cookie: String?, phone: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(MainActivity.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(SendPhoneService::class.java)
        val call = service.sendPhoneNumber(
            SendPhoneBody(
                cookie,
                phone
            )
        )
        call.enqueue(object : Callback<SendPhoneDto> {
            override fun onResponse(
                call: Call<SendPhoneDto>,
                response: Response<SendPhoneDto>
            ) {
                if (response.code() == 200) {
                    _requestResponse.value = response.body().message_sent
                }
            }

            override fun onFailure(call: Call<SendPhoneDto>, t: Throwable) {
                Log.i("SendPhoneDto", t.toString())
            }
        })
    }
}