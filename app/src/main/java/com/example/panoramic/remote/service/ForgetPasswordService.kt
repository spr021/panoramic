package com.example.panoramic.remote.service

import com.example.panoramic.remote.model.SendNewPasswordBody
import com.example.panoramic.remote.model.SendNewPasswordDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ForgetPasswordService {
    @POST("Home/Pass_Edit")
    fun sendNewPassword(@Body sendNewPasswordBody: SendNewPasswordBody): Call<SendNewPasswordDto>
}