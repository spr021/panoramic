package com.example.panoramic.app.ui.announcements.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.panoramic.app.MainActivity
import com.example.panoramic.data.entity.AnnouncementsEntity
import com.example.panoramic.remote.model.AnnouncementsDto
import com.example.panoramic.remote.model.TopTenSellerDto
import com.example.panoramic.remote.service.AnnouncementsService
import com.example.panoramic.remote.service.TopTenSellerService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AnnouncementsViewModel : ViewModel() {
    private val _viewState: MutableLiveData<AnnouncementsDto> = MutableLiveData()
    val viewState: LiveData<AnnouncementsDto>
    get() = _viewState

    fun getAnnouncementsList(cookie: String?) {
        val retrofit = Retrofit.Builder()
            .baseUrl(MainActivity.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(AnnouncementsService::class.java)
        val call = service.getAnnouncementList(cookie!!)
        call.enqueue(object : Callback<AnnouncementsDto> {
            override fun onResponse(call: Call<AnnouncementsDto>, response: Response<AnnouncementsDto>) {
                if (response.code() == 200) {
                    _viewState.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<AnnouncementsDto>, t: Throwable) {

            }
        })
    }
}