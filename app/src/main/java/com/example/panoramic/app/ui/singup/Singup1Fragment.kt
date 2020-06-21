package com.example.panoramic.app.ui.singup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.panoramic.R
import com.example.panoramic.databinding.FragmentSingup1Binding

class Singup1Fragment : Fragment(R.layout.fragment_singup1) {

    private var fragmentSingup1Fragment: FragmentSingup1Binding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSingup1Binding.bind(view)
        fragmentSingup1Fragment = binding

    }

    override fun onDestroyView() {
        fragmentSingup1Fragment = null
        super.onDestroyView()
    }
}