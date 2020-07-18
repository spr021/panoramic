package com.example.panoramic.remote.service

import com.example.panoramic.remote.model.UserInfoDta
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserInfoService {
    @GET("Home")
    fun getUserInfo(@Query("cookie") cookie: String): Call<UserInfoDta>
}