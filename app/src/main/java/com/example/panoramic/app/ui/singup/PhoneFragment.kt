package com.example.panoramic.app.ui.singup

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.panoramic.R
import com.example.panoramic.app.ui.singup.viewmodel.PhoneViewModel
import com.example.panoramic.databinding.FragmentPhoneBinding

class PhoneFragment : Fragment(R.layout.fragment_phone) {

    private var fragmentPhoneBinding: FragmentPhoneBinding? = null
    private lateinit var viewModel: PhoneViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentPhoneBinding.bind(view)
        fragmentPhoneBinding = binding
        viewModel = ViewModelProviders.of(this).get(PhoneViewModel::class.java)

        binding.phoneButton.setOnClickListener {
            val cookie = activity?.getSharedPreferences("COOKIE", Context.MODE_PRIVATE)!!
                .getString("COOKIE", "")
            val phoneNumber = binding.phoneInput.text.toString()
            viewModel.sendPhoneNumber(cookie, phoneNumber)
            findNavController().navigate(R.id.action_phoneFragment_to_SMSFragment)

        }

    }

    override fun onDestroyView() {
        fragmentPhoneBinding = null
        super.onDestroyView()
    }
}