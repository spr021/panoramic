package com.example.panoramic.remote.service

import com.example.panoramic.remote.model.LoginResponseDto
import com.example.panoramic.remote.model.LogoutResponseDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LoginService {
    @GET("Register")
    fun loginUser(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("cookie") cookie: String,
        @Query("notification_token") notification_token: String,
        @Query("mac_address") mac_address: String
    ): Call<LoginResponseDto>
}

interface LogoutService {
    @GET("Home/{id}/edit")
    fun logoutUser(
        @Path("id") userId: Int,
        @Query("cookie") cookie: String
    ): Call<LogoutResponseDto>
}
