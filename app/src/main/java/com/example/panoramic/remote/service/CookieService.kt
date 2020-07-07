package com.example.panoramic.remote.service

import com.example.panoramic.remote.model.CookieResponseDto
import retrofit2.Call
import retrofit2.http.GET

interface CookieService {
    @GET("Cookie")
    fun getCookie(): Call<CookieResponseDto>
}