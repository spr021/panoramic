package com.example.panoramic.app.ui.registerproduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class ConformationViewModelFactory(private val args: ConformationFragmentArgs): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConformationViewModel::class.java)){
            return ConformationViewModel(args) as T
        }
        throw IllegalAccessException("Unknow ConformationViewModel Class")
    }
}