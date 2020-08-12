package com.example.panoramic.app.ui.announcements.details

import androidx.lifecycle.ViewModel
import com.example.panoramic.app.MainActivity
import com.example.panoramic.remote.model.SendAnnouncementBody
import com.example.panoramic.remote.model.SendAnnouncementDto
import com.example.panoramic.remote.service.AnnouncementsSeenService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AnnouncementItemViewModel(val args: AnnouncementItemFragmentArgs) : ViewModel() {

    fun seenAnnouncements(cookie: String?, id: Int) {
        val body = SendAnnouncementBody(
            id,
            cookie!!
        )
        val retrofit = Retrofit.Builder()
            .baseUrl(MainActivity.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(AnnouncementsSeenService::class.java)
        val call = service.seenAnnouncementItem(body)
        call.enqueue(object : Callback<SendAnnouncementDto> {
            override fun onResponse(call: Call<SendAnnouncementDto>, response: Response<SendAnnouncementDto>) {}
            override fun onFailure(call: Call<SendAnnouncementDto>, t: Throwable) {}
        })
    }
}