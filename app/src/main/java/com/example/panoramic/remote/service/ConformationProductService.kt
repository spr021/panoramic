package com.example.panoramic.remote.service

import com.example.panoramic.remote.model.ConformationProductBody
import com.example.panoramic.remote.model.ConformationProductDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface ConformationProductService {
    @POST("Product")
        fun confirmProduct(@Body conformationProductBody: ConformationProductBody): Call<ConformationProductDto>
}