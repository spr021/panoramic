package com.example.panoramic.app.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class HomeViewModel(args: HomeFragmentArgs): ViewModel() {

    private val _confirmSuccess = MutableLiveData<Boolean>()
    val confirmSuccess: LiveData<Boolean>
        get() = _confirmSuccess

    private val _modelNumber = MutableLiveData<String>()
    private val _serialNumber = MutableLiveData<String>()

    private val _args = MutableLiveData<Array<String?>>()
    val args: LiveData<Array<String?>>
        get() = _args

    init {
        _modelNumber.value = args.modelNumber
        _serialNumber.value = args.serialNumber
        _args.value = arrayOf(_modelNumber.value, _serialNumber.value)
        _confirmSuccess.value = args.confirmSuccess
    }

    fun onConfirmSuccess(){
        _confirmSuccess.value = false
    }

}