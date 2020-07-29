package com.example.panoramic.remote.service

import com.example.panoramic.remote.model.LogoutResponseDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LogoutService {
    @GET("Home/{id}/edit")
    fun logoutUser(@Path("id") userId: Int, @Query("cookie") cookie: String): Call<LogoutResponseDto>
}