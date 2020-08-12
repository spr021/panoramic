package com.example.panoramic.app.ui.login

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.panoramic.R
import com.example.panoramic.app.MainActivity
import com.example.panoramic.app.isOnline
import com.example.panoramic.databinding.FragmentLoginBinding
import com.example.panoramic.remote.model.CookieResponseDto
import com.example.panoramic.remote.model.LoginResponseDto
import com.example.panoramic.remote.service.CookieService
import com.example.panoramic.remote.service.LoginService
import com.google.firebase.iid.FirebaseInstanceId
import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.NetworkInterface
import java.util.*


class LoginFragment : Fragment(R.layout.fragment_login) {

    private var fragmentLoginBinding: FragmentLoginBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentLoginBinding.bind(view)
        fragmentLoginBinding = binding

        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)



        binding.login.setOnClickListener {
            if (isOnline(context)) {
                binding.progressBar.visibility = View.VISIBLE
                loginUser()
            } else {
                val errorText = view.findViewById<TextView>(R.id.message)
                errorText?.text = "مشکل اینترنت"
                errorText?.visibility = View.VISIBLE
            }

            hideKeyboard()
        }
        binding.singup.setOnClickListener {
            view.findNavController().navigate(R.id.action_loginFragment_to_phoneFragment)
        }
        binding.forgetPassword.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_loginFragment_to_forgetpasswordPhoneFragment)
        }

    }

    private fun loginUser() {
        val notificationToken = FirebaseInstanceId.getInstance().token
        val username = fragmentLoginBinding!!.username.text.toString().trim()
        val password = fragmentLoginBinding!!.password.text.toString().trim()
        if(username.nonEmpty() && password.nonEmpty()) {
            val sharedPref = activity?.getSharedPreferences("COOKIE", Context.MODE_PRIVATE)
            val cookie = sharedPref!!.getString("COOKIE", "")
            val retrofit = Retrofit.Builder()
                .baseUrl(MainActivity.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(LoginService::class.java)
            val call = service.loginUser(username, password, cookie!!, notificationToken!!, getMACAddress())
            call.enqueue(object : Callback<LoginResponseDto> {
                override fun onResponse(
                    call: Call<LoginResponseDto>,
                    response: Response<LoginResponseDto>
                ) {
                    if (response.code() == 200) {
                        if (response.body()!!.success) {
                            view!!.findNavController()
                                .navigate(R.id.action_loginFragment_to_homeFragment)
                        } else {
                            val errorText = view?.findViewById<TextView>(R.id.message)
                            errorText?.text = "کاربری با این نام کاربری و رمز عبور یافت نشد"
                            errorText?.visibility = View.VISIBLE
                            view?.findViewById<ProgressBar>(R.id.progressBar)!!.visibility = View.GONE
                        }
                    }
                }

                override fun onFailure(call: Call<LoginResponseDto>, t: Throwable) {
                    val errorText = view?.findViewById<TextView>(R.id.message)
                    errorText?.text = "دوباره تلاش کنید"
                    errorText?.visibility = View.VISIBLE
                }
            })
        } else {
            val errorText = view?.findViewById<TextView>(R.id.message)
            errorText?.text = "نام کاربری و رمز عبور را وارد کنید"
            errorText?.visibility = View.VISIBLE
        }
    }

    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun getMACAddress(): String {
        try {
            val all: List<NetworkInterface> = Collections.list(NetworkInterface.getNetworkInterfaces())
            for (nif in all) {
                if (nif.name != "wlan0") continue
                val macBytes: ByteArray = nif.hardwareAddress ?: return ""
                val sb = StringBuilder(18)
                for (b in macBytes) {
                    if (sb.isNotEmpty())
                        sb.append(':')
                    sb.append(String.format("%02x", b))
                }
                return sb.toString()
            }
        } catch (ex: Exception) {
        }
        return "02:00:00:00:00:00"
    }

    override fun onDestroyView() {
        fragmentLoginBinding = null
        super.onDestroyView()
    }

}