package com.example.panoramic.app

import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.panoramic.R


class SplashFragment : Fragment(R.layout.activity_splash) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.window!!.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        if(!isOnline(context)) {
                CustomToast(requireActivity(), "لطفا به اینترنت متصل شوید", R.color.yellow)
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                activity?.window!!.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
//        else {
//            Handler().postDelayed({
//                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
//            }, 5000)
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.window!!.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }
}