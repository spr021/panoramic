package com.example.panoramic.app.ui.personalinformation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.panoramic.R
import com.example.panoramic.databinding.FragmentPersonalInformationBinding

class PersonalInformation : Fragment(R.layout.fragment_personal_information) {

    private var fragmentPersonalInformationBinding: FragmentPersonalInformationBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentPersonalInformationBinding.bind(view)
        fragmentPersonalInformationBinding = binding
    }

    override fun onDestroyView() {
        fragmentPersonalInformationBinding = null
        super.onDestroyView()
    }

}