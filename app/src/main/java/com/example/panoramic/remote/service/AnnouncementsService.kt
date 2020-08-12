package com.example.panoramic.remote.service


import com.example.panoramic.remote.model.AnnouncementsDto
import com.example.panoramic.remote.model.SendAnnouncementBody
import com.example.panoramic.remote.model.SendAnnouncementDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AnnouncementsService {
    @GET("Notif")
    fun getAnnouncementList(@Query("cookie") cookie: String): Call<AnnouncementsDto>
}

interface AnnouncementsSeenService {
    @POST("Notif")
    fun seenAnnouncementItem(@Body sendAnnouncementBody: SendAnnouncementBody): Call<SendAnnouncementDto>
}

