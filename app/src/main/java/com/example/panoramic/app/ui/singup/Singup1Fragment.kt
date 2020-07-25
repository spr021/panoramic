package com.example.panoramic.app.ui.singup

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

        binding.phoneNumberValue.setText(Singup1FragmentArgs.fromBundle(requireArguments()).phoneNumber)

        val name = binding.nameValue.text.toString()
        val family = binding.nameValue.text.toString()
        val email = binding.nameValue.text.toString()
        val shopName = binding.nameValue.text.toString()
        val shopKeeper = binding.nameValue.text.toString()
        val shopAddress = binding.nameValue.text.toString()
        val shopZip = binding.nameValue.text.toString()

        val firstPageInfo = arrayOf(name, family, email, shopName, shopKeeper, shopAddress, shopZip)


        binding.nextPage.setOnClickListener {
            findNavController().navigate(Singup1FragmentDirections.actionSingup1FragmentToSingup2Fragment(firstPageInfo))
        }

    }

    override fun onDestroyView() {
        fragmentSingup1Fragment = null
        super.onDestroyView()
    }

}