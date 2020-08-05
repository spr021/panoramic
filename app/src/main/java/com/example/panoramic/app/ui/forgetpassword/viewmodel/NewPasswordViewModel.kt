package com.example.panoramic.app.ui.forgetpassword.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.panoramic.app.MainActivity
import com.example.panoramic.remote.model.SendNewPasswordBody
import com.example.panoramic.remote.model.SendNewPasswordDto
import com.example.panoramic.remote.service.ForgetPasswordService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewPasswordViewModel : ViewModel() {

    private val _requestResponse = MutableLiveData<Boolean>()
    val requestResponse: LiveData<Boolean>
        get() = _requestResponse

    fun sendNewPassword(password: String, cookie: String?) {
        val body = SendNewPasswordBody(cookie, password)
        val retrofit = Retrofit.Builder()
            .baseUrl(MainActivity.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ForgetPasswordService::class.java)
        val call = service.sendNewPassword(body)
        call.enqueue(object : Callback<SendNewPasswordDto> {
            override fun onResponse(
                call: Call<SendNewPasswordDto>,
                response: Response<SendNewPasswordDto>
            ) {
                if (response.code() == 200) {
                    _requestResponse.value = response.body()!!.success
                }
            }

            override fun onFailure(call: Call<SendNewPasswordDto>, t: Throwable) {
                Log.i("SendPhoneDto", t.toString())
            }
        })
    }
}