package com.example.panoramic.app.ui.awards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.panoramic.app.MainActivity
import com.example.panoramic.remote.model.AnnouncementsDto
import com.example.panoramic.remote.model.ProductListDto
import com.example.panoramic.remote.service.AnnouncementsService
import com.example.panoramic.remote.service.ProductListService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AwardsGuideViewModel : ViewModel() {

    private val _viewState: MutableLiveData<ProductListDto> = MutableLiveData()
    val viewState: LiveData<ProductListDto>
        get() = _viewState

    fun getAnnouncementsList(cookie: String?) {
        val retrofit = Retrofit.Builder()
            .baseUrl(MainActivity.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ProductListService::class.java)
        val call = service.getProductList(cookie!!)
        call.enqueue(object : Callback<ProductListDto> {
            override fun onResponse(call: Call<ProductListDto>, response: Response<ProductListDto>) {
                if (response.code() == 200) {
                    _viewState.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<ProductListDto>, t: Throwable) {

            }
        })
    }
}