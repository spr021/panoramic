package com.example.panoramic.app.ui.singup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.panoramic.R
import com.example.panoramic.databinding.FragmentSmsBinding

class SMSFragment : Fragment(R.layout.fragment_sms) {

    private var fragmentSMSBinding: FragmentSmsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSmsBinding.bind(view)
        fragmentSMSBinding = binding
        binding.nextPage.setOnClickListener {
            findNavController().navigate(R.id.action_SMSFragment_to_singup1Fragment, null, null, null)
        }

    }

    override fun onDestroyView() {
        fragmentSMSBinding = null
        super.onDestroyView()
    }
}