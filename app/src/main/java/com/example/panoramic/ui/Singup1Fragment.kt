package com.example.panoramic.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.panoramic.R
import com.example.panoramic.databinding.FragmentSingup1Binding

class Singup1Fragment : Fragment(R.layout.fragment_singup1) {

    private var fragmentSingup1Fragment: FragmentSingup1Binding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSingup1Binding.bind(view)
        fragmentSingup1Fragment = binding
        binding.nextPage.setOnClickListener {
            findNavController().navigate(R.id.action_singup1Fragment_to_singup2Fragment, null, null, null)
        }

    }

    override fun onDestroyView() {
        fragmentSingup1Fragment = null
        super.onDestroyView()
    }
}