package com.example.panoramic.remote.service

import com.example.panoramic.remote.model.ProductInquiryDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductInquiryService {
    @GET("Product")
    fun sendNewProduct(@Query("customer_name") customerName: String, @Query("customer_phone") customerPhone: String, @Query("product_key") productKey: String ,@Query("cookie") cookie: String): Call<ProductInquiryDto>
}