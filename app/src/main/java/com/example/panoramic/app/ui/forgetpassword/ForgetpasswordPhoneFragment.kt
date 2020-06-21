package com.example.panoramic.app.ui.forgetpassword

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.panoramic.R
import com.example.panoramic.databinding.FragmentForgetpasswordBinding

class ForgetpasswordPhoneFragment : Fragment(R.layout.fragment_forgetpassword) {

    private var fragmentForgetpasswordPhoneBinding: FragmentForgetpasswordBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentForgetpasswordBinding.bind(view)
        fragmentForgetpasswordPhoneBinding = binding

    }

    override fun onDestroyView() {
        fragmentForgetpasswordPhoneBinding = null
        super.onDestroyView()
    }
}