package com.example.panoramic.app.ui.login

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.panoramic.R
import com.example.panoramic.databinding.FragmentLoginBinding
import com.example.panoramic.remote.model.CookieResponseDto
import com.example.panoramic.remote.service.CookieService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var fragmentLoginBinding: FragmentLoginBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentLoginBinding.bind(view)
        fragmentLoginBinding = binding

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getCurrentData()
    }

    private fun getCurrentData() {
        val sharedPref = activity?.getSharedPreferences("COOKIE", Context.MODE_PRIVATE)
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(CookieService::class.java)
        val call = service.getCookie()
        call.enqueue(object : Callback<CookieResponseDto> {
            override fun onResponse(call: Call<CookieResponseDto>, response: Response<CookieResponseDto>) {
                if (response.code() == 200) {
                    sharedPref!!.edit().putString("COOKIE", response.body().cookie).apply()

                }
            }
            override fun onFailure(call: Call<CookieResponseDto>, t: Throwable) {

                val errorText = view?.findViewById<TextView>(R.id.message)
                errorText?.text = t.message
                errorText?.visibility = View.VISIBLE
            }
        })
    }

    override fun onDestroyView() {
        fragmentLoginBinding = null
        super.onDestroyView()
    }

    companion object {
        var BaseUrl = "http://app.panoramic.co.ir/"
    }
}