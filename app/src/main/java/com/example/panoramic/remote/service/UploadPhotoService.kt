package com.example.panoramic.remote.service

import com.example.panoramic.remote.model.UploadPhotoDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UploadPhotoService {
    @Multipart
    @POST("Photo")
    fun updatePhoto(
        @Part photo: MultipartBody.Part,
        @Part("cookie") cookie: RequestBody
    ): Call<UploadPhotoDto>
}