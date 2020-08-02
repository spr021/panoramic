package com.example.panoramic.remote.service

import com.example.panoramic.remote.model.ChangePasswordBody
import com.example.panoramic.remote.model.ChangePasswordDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ChangePasswordService {
    @POST("Home/Pass_Edit")
    fun changePassword(@Body changePasswordBody: ChangePasswordBody): Call<ChangePasswordDto>
}