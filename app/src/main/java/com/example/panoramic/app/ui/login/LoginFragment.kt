package com.example.panoramic.app.ui.movies

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.panoramic.R
import com.example.panoramic.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var fragmentLoginBinding: FragmentLoginBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentLoginBinding.bind(view)
        fragmentLoginBinding = binding

        binding.singup.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_announcementsFragment)
        }

    }

    override fun onDestroyView() {
        fragmentLoginBinding = null
        super.onDestroyView()
    }
}