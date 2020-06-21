package com.example.panoramic.app.ui.registerproduct

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.panoramic.R
import com.example.panoramic.databinding.FragmentRegisterProductBinding

class RegisterProductFragment : Fragment(R.layout.fragment_register_product) {

    private var fragmentRegisterProductBinding: FragmentRegisterProductBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentRegisterProductBinding.bind(view)
        fragmentRegisterProductBinding = binding
        binding.registerProductInquiry.setOnClickListener {
            findNavController().navigate(R.id.action_registerProductFragment_to_conformationFragment, null, null, null)
        }

    }

    override fun onDestroyView() {
        fragmentRegisterProductBinding = null
        super.onDestroyView()
    }
}