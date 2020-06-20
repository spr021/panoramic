package com.example.panoramic.app.ui.movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.panoramic.R
import com.example.panoramic.databinding.FragmentConformationBinding
import com.example.panoramic.databinding.FragmentForgetpasswordBinding
import com.example.panoramic.databinding.FragmentMoviesBinding
import com.example.panoramic.databinding.FragmentRegisterProductBinding
import com.example.panoramic.databinding.FragmentSmsBinding

class ForgetpasswordPhoneFragment : Fragment(R.layout.fragment_forgetpassword) {

    private var fragmentForgetpasswordBinding: FragmentForgetpasswordBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentForgetpasswordBinding.bind(view)
        fragmentForgetpasswordBinding = binding

    }

    override fun onDestroyView() {
        fragmentForgetpasswordBinding = null
        super.onDestroyView()
    }
}