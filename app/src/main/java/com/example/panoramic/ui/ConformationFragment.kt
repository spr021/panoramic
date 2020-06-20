package com.example.panoramic.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.panoramic.R
import com.example.panoramic.databinding.FragmentConformationBinding

class ConformationFragment : Fragment(R.layout.fragment_conformation) {

    private var fragmentConformationBinding: FragmentConformationBinding? = null

    private lateinit var modelNumber: String
    private lateinit var serialNumber: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentConformationBinding.bind(view)
        fragmentConformationBinding = binding
        modelNumber = binding.modelValue.text.toString()
        serialNumber = binding.serialValue.text.toString()
        binding.editingInformationButton.setOnClickListener {
            findNavController().navigate(ConformationFragmentDirections.actionConformationFragmentToRegisterProductFragment())
        }
        binding.confirmButton.setOnClickListener {
            findNavController().navigate(ConformationFragmentDirections.actionConformationFragmentToHomeFragment(modelNumber, serialNumber))
        }

    }

    override fun onDestroyView() {
        fragmentConformationBinding = null
        super.onDestroyView()
    }
}