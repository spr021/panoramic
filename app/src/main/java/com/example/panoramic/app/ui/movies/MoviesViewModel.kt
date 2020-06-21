package com.example.panoramic.app.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.panoramic.data.entity.MoviesEntity

class MoviesViewModel : ViewModel() {
    private val _viewState: MutableLiveData<MoviesEntity> = MutableLiveData()
    val viewState: LiveData<MoviesEntity> = _viewState

    fun request() {
        //send request and get json convert to data class with Gson()
    }
}