package com.example.panoramic.remote.service

import com.example.panoramic.remote.model.TopTenSellerDto
import retrofit2.Call
import retrofit2.http.*

interface TopTenSellerService {
    @GET("Home/Top10")
    fun getRankList(
        @Query("cookie") cookie: String
    ): Call<TopTenSellerDto>
}
