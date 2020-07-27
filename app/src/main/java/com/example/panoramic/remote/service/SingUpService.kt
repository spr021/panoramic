package com.example.panoramic.remote.service

import com.example.panoramic.remote.model.SingUpBody
import com.example.panoramic.remote.model.SingUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SingUpService {
    @POST("Home")
    fun singUp(@Body singUpBody: SingUpBody): Call<SingUpDto>
}