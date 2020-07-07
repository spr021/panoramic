package com.example.panoramic.app.ui.announcements.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.panoramic.app.ui.movies.details.MovieItemFragmentArgs
import com.example.panoramic.app.ui.movies.details.MovieItemViewModel

@Suppress("UNCHECKED_CAST")
class AnnouncementItemViewModelFactory(private val args: AnnouncementItemFragmentArgs): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnnouncementItemViewModel::class.java)){
            return AnnouncementItemViewModel(args) as T
        }
        throw IllegalAccessException("Unknow MovieItemViewModel Class")
    }
}