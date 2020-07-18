package com.example.panoramic.remote.service

import com.example.panoramic.remote.model.LoginResponseDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {
    @GET("Register")
    fun loginUser(@Query("username") username: String, @Query("password") password: String, @Query("cookie") cookie: String): Call<LoginResponseDto>
}