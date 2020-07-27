package com.example.panoramic.app.ui.singup.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.panoramic.app.MainActivity
import com.example.panoramic.remote.model.SendPhoneBody
import com.example.panoramic.remote.model.SendPhoneDto
import com.example.panoramic.remote.model.SingUpBody
import com.example.panoramic.remote.model.SingUpDto
import com.example.panoramic.remote.service.SendPhoneService
import com.example.panoramic.remote.service.SingUpService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Singup3ViewModel : ViewModel() {

    private val _requestResponse = MutableLiveData<Boolean>()
    val requestResponse: LiveData<Boolean>
        get() = _requestResponse

    fun singUp(singUpBody: Array<String?>) {
        val body = SingUpBody(
            cookie = singUpBody[0],
            name = singUpBody[1],
            last_name = singUpBody[2],
            email = singUpBody[3],
            shop_name = singUpBody[4],
            shopkeeper = singUpBody[5],
            shop_zip = singUpBody[6],
            shop_address = singUpBody[7],
            national_id = singUpBody[8],
            age = singUpBody[9],
            city = singUpBody[10],
            home_address = singUpBody[11],
            home_phone = singUpBody[12],
            home_zip = singUpBody[13],
            marital_status = singUpBody[14],
            child_number = singUpBody[15],
            edu_level = singUpBody[16],
            card_number = singUpBody[17],
            account_number = singUpBody[18],
            shaba = singUpBody[19],
            bank_name = singUpBody[20]
        )
        Log.i("body1", singUpBody.toString())
        Log.i("body2", body.toString())
        val retrofit = Retrofit.Builder()
            .baseUrl(MainActivity.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(SingUpService::class.java)
        val call = service.singUp(body)
        call.enqueue(object : Callback<SingUpDto> {
            override fun onResponse(call: Call<SingUpDto>, response: Response<SingUpDto>) {
                if (response.code() == 200) {
                    _requestResponse.value = response.body().success
                }
            }

            override fun onFailure(call: Call<SingUpDto>, t: Throwable) {
                Log.i("error", t.toString())
            }
        })
    }
}