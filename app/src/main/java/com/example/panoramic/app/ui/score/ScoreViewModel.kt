package com.example.panoramic.app.ui.score

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.panoramic.R
import com.example.panoramic.app.MainActivity
import com.example.panoramic.remote.model.Item
import com.example.panoramic.remote.model.Product
import com.example.panoramic.remote.model.ScoreDto
import com.example.panoramic.remote.model.TransactionDto
import com.example.panoramic.remote.service.ScoreService
import com.example.panoramic.remote.service.TransactionService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ScoreViewModel : ViewModel() {

    private val _productListSuccess = MutableLiveData<Boolean>()
    val productListSuccess: LiveData<Boolean>
        get() = _productListSuccess

    private val _userInfo = MutableLiveData<ScoreDto>()
    val userInfo: LiveData<ScoreDto>
        get() = _userInfo

    lateinit var productList: List<Product>

    fun getSellsProduct(cookie: String?) {
        val retrofit = Retrofit.Builder()
            .baseUrl(MainActivity.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ScoreService::class.java)
        val call = service.getSellsProduct(cookie!!)
        call.enqueue(object : Callback<ScoreDto> {
            override fun onResponse(
                call: Call<ScoreDto>,
                response: Response<ScoreDto>
            ) {
                if (response.code() == 200) {
                    _userInfo.value = response.body()
                    productList = response.body().items
                    _productListSuccess.value = response.body().success

                }
            }

            override fun onFailure(call: Call<ScoreDto>, t: Throwable) {
            }
        })
    }
}