package com.example.panoramic.remote.service

import com.example.panoramic.remote.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MovieService {
    @GET("Film")
    fun getMovieList(@Query("cookie") cookie: String): Call<MovieDto>
}

interface MovieSeenService {
    @POST("Film")
    fun seenMovieItem(@Body sendMovieBody: SendMovieBody): Call<SendMovieDto>
}
