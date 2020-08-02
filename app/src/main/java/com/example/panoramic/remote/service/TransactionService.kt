package com.example.panoramic.remote.service

import com.example.panoramic.remote.model.TransactionDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TransactionService {
    @GET("Transaction")
    fun getTransactionList(@Query("cookie") cookie: String): Call<TransactionDto>
}