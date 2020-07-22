package com.example.panoramic.app.ui.registerproduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.panoramic.app.MainActivity
import com.example.panoramic.remote.model.ProductInquiryDto
import com.example.panoramic.remote.service.ProductInquiryService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterProductViewModel(args: RegisterProductFragmentArgs) : ViewModel() {

    val args = args

    private val _productInfo = MutableLiveData<ProductInquiryDto?>()
    val productInfo: LiveData<ProductInquiryDto?>?
        get() = _productInfo


    fun getProductInfo(
        customerName: String,
        customerPhone: String,
        productKey: String,
        cookie: String?
    ) {
        val retrofit = Retrofit.Builder()
            .baseUrl(MainActivity.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ProductInquiryService::class.java)
        val call = service.sendNewProduct(customerName, customerPhone, productKey, cookie!!)
        call.enqueue(object : Callback<ProductInquiryDto> {
            override fun onResponse(
                call: Call<ProductInquiryDto>,
                response: Response<ProductInquiryDto>
            ) {
                if (response.code() == 200) {
                    _productInfo.value = response.body()
                }
            }

            override fun onFailure(call: Call<ProductInquiryDto>, t: Throwable) {
                //CustomToast(Activity(), "خطا در برقراری ارتباط", R.color.red)
            }
        })
    }

}