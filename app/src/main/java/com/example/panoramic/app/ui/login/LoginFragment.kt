package com.example.panoramic.app.ui.login

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.panoramic.R
import com.example.panoramic.databinding.FragmentLoginBinding
import com.example.panoramic.remote.model.CookieResponseDto
import com.example.panoramic.remote.model.LoginResponseDto
import com.example.panoramic.remote.service.CookieService
import com.example.panoramic.remote.service.LoginService
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


        binding.login.setOnClickListener {
            loginUser()
            hideKeyboard()
        }
        binding.singup.setOnClickListener {
            view.findNavController().navigate(R.id.action_loginFragment_to_phoneFragment)
        }
        binding.forgetPassword.setOnClickListener {
            view.findNavController().navigate(R.id.action_loginFragment_to_forgetpasswordPhoneFragment)
        }

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

    private fun loginUser() {
        val username = fragmentLoginBinding!!.username.text.toString().trim()
        val password = fragmentLoginBinding!!.password.text.toString().trim()
        val sharedPref = activity?.getSharedPreferences("COOKIE", Context.MODE_PRIVATE)
        val cookie = sharedPref!!.getString("COOKIE", "")
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(LoginService::class.java)
        val call = service.loginUser(username, password, cookie!!)
        call.enqueue(object : Callback<LoginResponseDto> {
            override fun onResponse(call: Call<LoginResponseDto>, response: Response<LoginResponseDto>) {
                if (response.code() == 200) {
                    if(response.body().success) {
                        view!!.findNavController()
                            .navigate(R.id.action_loginFragment_to_homeFragment)
                    } else {
                        val errorText = view?.findViewById<TextView>(R.id.message)
                        errorText?.text = "کاربری با این نام کاربری و پسورد یافت نشد"
                        errorText?.visibility = View.VISIBLE
                    }
                }
            }
            override fun onFailure(call: Call<LoginResponseDto>, t: Throwable) {
                val errorText = view?.findViewById<TextView>(R.id.message)
                errorText?.text = "دوباره تلاش کنید"
                errorText?.visibility = View.VISIBLE
            }
        })
    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onDestroyView() {
        fragmentLoginBinding = null
        super.onDestroyView()
    }

    companion object {
        var BaseUrl = "http://app.panoramic.co.ir/"
    }

}