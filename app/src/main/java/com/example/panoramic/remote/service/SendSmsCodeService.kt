package com.example.panoramic.remote.service

import com.example.panoramic.remote.model.SendSmsBody
import com.example.panoramic.remote.model.SendSmsCodeDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SendSmsCodeService {
    @POST("Sms")
    fun sendSmsCode(@Body sendPhoneBody: SendSmsBody): Call<SendSmsCodeDto>
}