package com.example.panoramic.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.panoramic.R
import com.example.panoramic.databinding.FragmentConformationBinding
import com.example.panoramic.databinding.FragmentMoviesBinding
import com.example.panoramic.databinding.FragmentRegisterProductBinding
import com.example.panoramic.databinding.FragmentSmsBinding

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private var fragmentMoviesBinding: FragmentMoviesBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMoviesBinding.bind(view)
        fragmentMoviesBinding = binding

    }

    override fun onDestroyView() {
        fragmentMoviesBinding = null
        super.onDestroyView()
    }
}