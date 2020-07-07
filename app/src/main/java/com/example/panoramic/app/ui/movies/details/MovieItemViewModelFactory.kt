package com.example.panoramic.app.ui.movies.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class MovieItemViewModelFactory(private val args: MovieItemFragmentArgs): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieItemViewModel::class.java)){
            return MovieItemViewModel(args) as T
        }
        throw IllegalAccessException("unknow MovieItemViewModel Class")
    }
}