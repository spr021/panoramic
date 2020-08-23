package com.example.panoramic.app

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.panoramic.R
import com.example.panoramic.databinding.ActivityMainBinding
import com.example.panoramic.remote.model.CookieResponseDto
import com.example.panoramic.remote.model.UserInfoDto
import com.example.panoramic.remote.service.CookieService
import com.example.panoramic.remote.service.UserInfoService
import com.google.firebase.messaging.FirebaseMessagingService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private val _userInfo = MutableLiveData<Boolean>()
    val userInfo: LiveData<Boolean>
        get() = _userInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //Getting the Navigation Controller
        navController = Navigation.findNavController(this, R.id.fragment)
        createNotificationChannel()

        val cookie =
            getSharedPreferences("COOKIE", Context.MODE_PRIVATE)!!.getString("COOKIE", null)
//        val fragmentManager: FragmentManager = supportFragmentManager
//        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()


        if (cookie != null) {
            getUserInfo(cookie)
            userInfo.observe(this, Observer {
                if (it) {
                    lunchFragment(R.id.homeFragment)
//                    if (navigation == "Product") {
//                        val scoreFragment = ScoreFragment()
//                        fragmentTransaction.replace(R.id.fragment, scoreFragment)
//                        fragmentTransaction.commit()
//                    }
                } else {
                    Log.i("OOOOOO", "OOOO")
                    lunchFragment(R.id.loginFragment)
                    //super.setTheme(R.style.SplashTheme)
                }
            })
        } else {
            getCurrentData()
        }

//        if (intent.extras != null) {
//            for (key in intent.extras!!.keySet()) {
//                val value = intent.extras!!.getString(key)
//                Log.i("GGGGGGGGGGGGG", "Key: $key Value: $value")
//            }
//        }
        //Setting the navigation controller to Bottom Nav
        bottom_navigation.setupWithNavController(navController)

        fun hideBottomNavigation() {
            bottom_navigation?.visibility = View.GONE
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.forgetpasswordPhoneFragment -> hideBottomNavigation()
                R.id.forgetpasswordSMSFragment -> hideBottomNavigation()
                R.id.newPasswordFragment -> hideBottomNavigation()
                R.id.loginFragment -> hideBottomNavigation()
                R.id.registerProductFragment -> hideBottomNavigation()
                R.id.conformationFragment -> hideBottomNavigation()
                R.id.scoreFragment -> hideBottomNavigation()
                R.id.phoneFragment -> hideBottomNavigation()
                R.id.singup1Fragment -> hideBottomNavigation()
                R.id.singup2Fragment -> hideBottomNavigation()
                R.id.singup3Fragment -> hideBottomNavigation()
                R.id.SMSFragment -> hideBottomNavigation()
                R.id.successSingupFragment -> hideBottomNavigation()
                R.id.playerFragment -> hideBottomNavigation()
                R.id.barcodeFragment -> hideBottomNavigation()
                R.id.questionFragment -> hideBottomNavigation()
                R.id.splashFragment -> hideBottomNavigation()
                else -> bottom_navigation.visibility = View.VISIBLE
            }
        }
        /*
        //for unCheck bottom navigation bar
        navigation.menu.setGroupCheckable(0, true, false)
        for (i in 0 until navigation.menu.size()) {
            navigation.menu.getItem(i).isChecked = false
        }
        navigation.menu.setGroupCheckable(0, true, true)
        */
    }

    private fun getCurrentData() {
        val sharedPref = getSharedPreferences("COOKIE", Context.MODE_PRIVATE)
        val retrofit = Retrofit.Builder()
            .baseUrl(MainActivity.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(CookieService::class.java)
        val call = service.getCookie()
        call.enqueue(object : Callback<CookieResponseDto> {
            override fun onResponse(
                call: Call<CookieResponseDto>,
                response: Response<CookieResponseDto>
            ) {
                if (response.code() == 200) {
                    sharedPref!!.edit().putString("COOKIE", response.body()?.cookie).apply()
                    lunchFragment(R.id.loginFragment)
                } else {
                    lunchFragment(R.id.loginFragment)
                }
            }

            override fun onFailure(call: Call<CookieResponseDto>, t: Throwable) {
                Log.i("getCurrentData", t.toString())
                lunchFragment(R.id.loginFragment)
            }
        })
    }

    fun getUserInfo(cookie: String?) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(UserInfoService::class.java)
        val call = service.getUserInfo(cookie)
        call.enqueue(object : Callback<UserInfoDto> {
            override fun onResponse(call: Call<UserInfoDto>, response: Response<UserInfoDto>) {
                if (response.code() == 200) {
                    _userInfo.value = response.body()?.success
                } else {
                    lunchFragment(R.id.loginFragment)
                }
            }

            @SuppressLint("CommitPrefEdits")
            override fun onFailure(call: Call<UserInfoDto>, t: Throwable) {
                Log.i("getUserInfo", t.toString())
                this@MainActivity.getSharedPreferences("COOKIE", Context.MODE_PRIVATE)!!.edit().clear()
                getCurrentData()
                lunchFragment(R.id.loginFragment)
            }
        })
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            // Create the Confirm Product Channel
            val confirmProductChannel = NotificationChannel("1", "Confirm Product", importance)
            confirmProductChannel.description = "When a product is registered by the customer"

            // Create the Notification Channel
            val notificationChannel = NotificationChannel("2", "Notification", importance)
            notificationChannel.description = "When add Movie or Announcement info"

            val notificationManager =
                getSystemService(FirebaseMessagingService.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(confirmProductChannel)
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

    fun lunchFragment(fragmentId: Int){
        val navGraph = navController.graph
        navGraph.startDestination = fragmentId
        navController.graph = navGraph
    }

    companion object {
        var BaseUrl = "http://app.panoramic.co.ir/"
    }
}