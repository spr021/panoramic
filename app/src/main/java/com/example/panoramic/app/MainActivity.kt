package com.example.panoramic.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.panoramic.R
import com.example.panoramic.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )

        //Getting the Navigation Controller
        navController = Navigation.findNavController(this,
            R.id.fragment
        )

        //Setting the navigation controller to Bottom Nav
        bottom_navigation.setupWithNavController(navController)

            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.forgetpasswordPhoneFragment -> bottom_navigation?.visibility = View.GONE
                    R.id.forgetpasswordSMSFragment -> bottom_navigation?.visibility = View.GONE
                    R.id.newPasswordFragment -> bottom_navigation?.visibility = View.GONE
                    R.id.loginFragment -> bottom_navigation?.visibility = View.GONE
                    R.id.registerProductFragment -> bottom_navigation?.visibility = View.GONE
                    R.id.conformationFragment -> bottom_navigation?.visibility = View.GONE
                    R.id.scoreFragment -> bottom_navigation?.visibility = View.GONE
                    R.id.phoneFragment -> bottom_navigation?.visibility = View.GONE
                    R.id.singup1Fragment -> bottom_navigation?.visibility = View.GONE
                    R.id.singup2Fragment -> bottom_navigation?.visibility = View.GONE
                    R.id.singup3Fragment -> bottom_navigation?.visibility = View.GONE
                    R.id.SMSFragment -> bottom_navigation?.visibility = View.GONE
                    R.id.successSingupFragment -> bottom_navigation?.visibility = View.GONE
                    R.id.playerFragment -> bottom_navigation?.visibility = View.GONE
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


    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }
}