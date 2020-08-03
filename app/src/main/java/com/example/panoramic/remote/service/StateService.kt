package com.example.panoramic.remote.service

import com.example.panoramic.remote.model.LoginResponseDto
import com.example.panoramic.remote.model.StateDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StateService {
    @GET("State")
    fun getState(
        @Query("cookie") cookie: String
    ): Call<StateDto>
}

interface CityService {
    @GET("State")
    fun getCity(
        @Query("cookie") cookie: String,
        @Query("state") state: Int
    ): Call<StateDto>
}