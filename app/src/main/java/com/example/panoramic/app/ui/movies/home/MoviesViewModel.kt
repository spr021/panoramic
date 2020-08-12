package com.example.panoramic.app.ui.movies.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.panoramic.app.MainActivity
import com.example.panoramic.data.entity.MoviesEntity
import com.example.panoramic.remote.model.AnnouncementsDto
import com.example.panoramic.remote.model.MovieDto
import com.example.panoramic.remote.service.AnnouncementsService
import com.example.panoramic.remote.service.MovieService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesViewModel : ViewModel() {
    private val _viewState: MutableLiveData<MovieDto> = MutableLiveData()
    val viewState: LiveData<MovieDto> = _viewState

    fun getMovieList(cookie: String?) {
        val retrofit = Retrofit.Builder()
            .baseUrl(MainActivity.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(MovieService::class.java)
        val call = service.getMovieList(cookie!!)
        call.enqueue(object : Callback<MovieDto> {
            override fun onResponse(call: Call<MovieDto>, response: Response<MovieDto>) {
                if (response.code() == 200) {
                    _viewState.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<MovieDto>, t: Throwable) {

            }
        })
    }
}