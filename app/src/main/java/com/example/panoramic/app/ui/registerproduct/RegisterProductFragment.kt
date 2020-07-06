package com.example.panoramic.app.ui.registerproduct

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.panoramic.R
import com.example.panoramic.app.ui.home.HomeFragmentArgs
import com.example.panoramic.databinding.FragmentRegisterProductBinding
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import kotlinx.android.synthetic.main.fragment_register_product.*

class RegisterProductFragment : Fragment(R.layout.fragment_register_product) {

    private var fragmentRegisterProductBinding: FragmentRegisterProductBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentRegisterProductBinding.bind(view)
        fragmentRegisterProductBinding = binding

        val args = RegisterProductFragmentArgs.fromBundle(arguments!!)

        binding.registerProductInquiry.setOnClickListener {
            findNavController().navigate(R.id.action_registerProductFragment_to_conformationFragment, null, null, null)
        }
        binding.registerProductBarcodeButton.setOnClickListener {
            findNavController().navigate(R.id.action_registerProductFragment_to_barcodeFragment)
        }

        binding.registerProductBarcodeInput.setText(args.barcode)

    }



    override fun onDestroyView() {
        fragmentRegisterProductBinding = null
        super.onDestroyView()
    }
}