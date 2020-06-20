package com.example.panoramic.app.ui.movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.panoramic.R
import com.example.panoramic.databinding.FragmentConformationBinding
import com.example.panoramic.databinding.FragmentMoviesBinding
import com.example.panoramic.databinding.FragmentPhoneBinding
import com.example.panoramic.databinding.FragmentRegisterProductBinding
import com.example.panoramic.databinding.FragmentSmsBinding

class PhoneFragment : Fragment(R.layout.fragment_phone) {

    private var fragmentPhoneBinding: FragmentPhoneBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentPhoneBinding.bind(view)
        fragmentPhoneBinding = binding

    }

    override fun onDestroyView() {
        fragmentPhoneBinding = null
        super.onDestroyView()
    }
}