package com.example.panoramic.remote.service

import com.example.panoramic.remote.model.ScoreDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ScoreService {
    @GET("Product/Score")
    fun getSellsProduct(@Query("cookie") cookie: String): Call<ScoreDto>
}