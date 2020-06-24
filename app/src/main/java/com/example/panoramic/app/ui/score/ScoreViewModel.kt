package com.example.panoramic.app.ui.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.panoramic.data.entity.ScoreEntity

class ScoreViewModel : ViewModel() {
    private val _viewState: MutableLiveData<ScoreEntity> = MutableLiveData()
    val viewState: LiveData<ScoreEntity> = _viewState

    fun request() {
        //send request and get json convert to data class with Gson()
    }
}