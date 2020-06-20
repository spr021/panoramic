package com.example.panoramic.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.panoramic.R
import com.example.panoramic.databinding.ActivityHomeBinding
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_home
        )

        //Getting the Navigation Controller
        navController = Navigation.findNavController(this,
            R.id.fragment
        )

        //Setting the navigation controller to Bottom Nav
        bottom_navigation.setupWithNavController(navController)

            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.registerProductFragment -> bottom_navigation?.visibility = View.GONE
                    R.id.conformationFragment -> bottom_navigation?.visibility = View.GONE
                    else -> bottom_navigation.visibility = View.VISIBLE
                }
        }
        /*
        //for unCheck bottom navigation bar
        bottom_navigation.menu.setGroupCheckable(0, true, false)
        for (i in 0 until bottom_navigation.menu.size()) {
            bottom_navigation.menu.getItem(i).isChecked = false
        }
        bottom_navigation.menu.setGroupCheckable(0, true, true)
        */

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }
}