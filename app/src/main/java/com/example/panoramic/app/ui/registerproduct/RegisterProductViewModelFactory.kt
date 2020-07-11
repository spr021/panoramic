package com.example.panoramic.app.ui.registerproduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class RegisterProductViewModelFactory(private val args: RegisterProductFragmentArgs): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterProductViewModel::class.java)){
            return RegisterProductViewModel(args) as T
        }
        throw IllegalAccessException("Unknow RegisterProductViewModel Class")
    }
}