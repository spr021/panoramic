package com.example.panoramic.app.ui.personalinformation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.panoramic.app.MainActivity
import com.example.panoramic.remote.model.LogoutResponseDto
import com.example.panoramic.remote.model.UserInfoDto
import com.example.panoramic.remote.service.LogoutService
import com.example.panoramic.remote.service.UserInfoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PersonalInformationViewModel: ViewModel() {
    private val _userInfo = MutableLiveData<UserInfoDto?>()
    val userInfo: LiveData<UserInfoDto?>?
        get() = _userInfo

    private val _userLogout = MutableLiveData<Boolean>()
    val userLogout: LiveData<Boolean>?
        get() = _userLogout

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

    fun logout(userId: Int, cookie: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(MainActivity.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(LogoutService::class.java)
        val call = service.logoutUser(userId, cookie)
        call.enqueue(object : Callback<LogoutResponseDto> {
            override fun onResponse(call: Call<LogoutResponseDto>, response: Response<LogoutResponseDto>) {
                if (response.code() == 200) {
                    _userLogout.value = response.body().success
                    Log.i("sasa", _userInfo.value.toString())
                }
            }

            override fun onFailure(call: Call<LogoutResponseDto>, t: Throwable) {

            }
        })
    }

    fun uploadProfilePhoto() {

    }
}

