package com.example.panoramic.app.ui.announcements

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.panoramic.data.entity.AnnouncementsEntity

class AnnouncementsViewModel : ViewModel() {
    private val _viewState: MutableLiveData<AnnouncementsEntity> = MutableLiveData()
    val viewState: LiveData<AnnouncementsEntity> = _viewState

    fun request() {
        //send request and get json convert to data class with Gson()
    }
}