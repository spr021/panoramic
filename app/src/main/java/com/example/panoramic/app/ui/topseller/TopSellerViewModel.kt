package com.example.panoramic.app.ui.topseller

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.panoramic.app.MainActivity
import com.example.panoramic.remote.model.TopTenSellerDto
import com.example.panoramic.remote.service.TopTenSellerService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TopSellerViewModel : ViewModel() {

    private val _getList = MutableLiveData<TopTenSellerDto>()
    val getList: LiveData<TopTenSellerDto>
        get() = _getList

    fun getRankList(cookie: String?) {
        val retrofit = Retrofit.Builder()
            .baseUrl(MainActivity.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(TopTenSellerService::class.java)
        val call = service.getRankList(cookie!!)
        call.enqueue(object : Callback<TopTenSellerDto> {
            override fun onResponse(call: Call<TopTenSellerDto>, response: Response<TopTenSellerDto>) {
                if (response.code() == 200) {
                    _getList.value = response.body()
                }
            }
            override fun onFailure(call: Call<TopTenSellerDto>, t: Throwable) {

            }
        })
    }
}