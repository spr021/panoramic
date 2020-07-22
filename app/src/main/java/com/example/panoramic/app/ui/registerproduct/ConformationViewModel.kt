package com.example.panoramic.app.ui.registerproduct

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.panoramic.app.MainActivity
import com.example.panoramic.remote.model.ConformationProductBody
import com.example.panoramic.remote.model.ConformationProductDto
import com.example.panoramic.remote.service.ConformationProductService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConformationViewModel(val args: ConformationFragmentArgs) : ViewModel() {

    private val _confirmationProductResponse = MutableLiveData<ConformationProductDto>()
    val confirmationProductResponse: LiveData<ConformationProductDto>?
        get() = _confirmationProductResponse

    fun confirmProduct(
        cookie: String?
    ) {
        val retrofit = Retrofit.Builder()
            .baseUrl(MainActivity.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ConformationProductService::class.java)
        val call = service.confirmProduct(
            ConformationProductBody(
                args.customerName,
                args.customerPhone,
                args.serialNumber,
                cookie!!,
                args.productId
            )
        )
        call.enqueue(object : Callback<ConformationProductDto> {
            override fun onResponse(
                call: Call<ConformationProductDto>,
                response: Response<ConformationProductDto>
            ) {
                if (response.code() == 200) {
                    _confirmationProductResponse.value = response.body()
                }
            }

            override fun onFailure(call: Call<ConformationProductDto>, t: Throwable) {
                Log.i("sasa", t.toString())
            }
        })
    }

}