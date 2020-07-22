package com.example.panoramic.app.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.panoramic.app.MainActivity
import com.example.panoramic.remote.model.UserInfoDto
import com.example.panoramic.remote.service.UserInfoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeViewModel(args: HomeFragmentArgs) : ViewModel() {

    private val _confirmSuccess = MutableLiveData<Boolean>()
    val confirmSuccess: LiveData<Boolean>
        get() = _confirmSuccess

    private val _modelNumber = MutableLiveData<String>()
    private val _serialNumber = MutableLiveData<String>()

    private val _args = MutableLiveData<Array<String?>>()
    val args: LiveData<Array<String?>>
        get() = _args
    private val _userInfo = MutableLiveData<UserInfoDto?>()
    val userInfo: LiveData<UserInfoDto?>?
        get() = _userInfo

    init {
        _modelNumber.value = args.modelNumber
        _serialNumber.value = args.serialNumber
        _args.value = arrayOf(_modelNumber.value, _serialNumber.value)
        _confirmSuccess.value = args.confirmSuccess
    }

    fun onConfirmSuccess() {
        _confirmSuccess.value = false
    }

    fun getUserInfo(cookie: String?) {
        val retrofit = Retrofit.Builder()
            .baseUrl(MainActivity.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(UserInfoService::class.java)
        val call = service.getUserInfo(cookie)
        call.enqueue(object : Callback<UserInfoDto> {
            override fun onResponse(call: Call<UserInfoDto>, response: Response<UserInfoDto>) {
                if (response.code() == 200) {
                    _userInfo.value = response.body()
                    Log.i("sasa", _userInfo.value.toString())
                }
            }

            override fun onFailure(call: Call<UserInfoDto>, t: Throwable) {

            }
        })
    }





}
