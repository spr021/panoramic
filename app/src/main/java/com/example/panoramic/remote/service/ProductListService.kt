package com.example.panoramic.remote.service

import com.example.panoramic.remote.model.ProductListDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductListService {
    @GET("All")
    fun getProductList(@Query("cookie") cookie: String): Call<ProductListDto>
}