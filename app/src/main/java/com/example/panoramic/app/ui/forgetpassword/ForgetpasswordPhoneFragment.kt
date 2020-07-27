package com.example.panoramic.app.ui.forgetpassword

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.panoramic.R
import com.example.panoramic.app.ui.forgetpassword.viewmodel.ForgetpasswordPhoneViewModel
import com.example.panoramic.databinding.FragmentForgetpasswordBinding

class ForgetpasswordPhoneFragment : Fragment(R.layout.fragment_forgetpassword) {

    private var fragmentForgetpasswordPhoneBinding: FragmentForgetpasswordBinding? = null
    private lateinit var viewModel: ForgetpasswordPhoneViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentForgetpasswordBinding.bind(view)
        fragmentForgetpasswordPhoneBinding = binding
        viewModel = ViewModelProviders.of(this).get(ForgetpasswordPhoneViewModel::class.java)

        binding.phoneButton.setOnClickListener {
            val cookie = activity?.getSharedPreferences("COOKIE", Context.MODE_PRIVATE)!!
                .getString("COOKIE", "")
            val phoneNumber = binding.phoneInput.text.toString()
            viewModel.sendPhoneNumber(cookie, phoneNumber)
            findNavController().navigate(R.id.action_forgetpasswordPhoneFragment_to_forgetpasswordSMSFragment)

        }
    }

    override fun onDestroyView() {
        fragmentForgetpasswordPhoneBinding = null
        super.onDestroyView()
    }
}