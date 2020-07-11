package com.example.panoramic.app.ui.registerproduct

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.panoramic.R
import com.example.panoramic.databinding.FragmentRegisterProductBinding

class RegisterProductFragment : Fragment(R.layout.fragment_register_product) {

    private var fragmentRegisterProductBinding: FragmentRegisterProductBinding? = null
    private lateinit var viewModel: RegisterProductViewModel
    private lateinit var viewModelFactory: RegisterProductViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentRegisterProductBinding.bind(view)
        fragmentRegisterProductBinding = binding

        viewModelFactory =
            RegisterProductViewModelFactory(RegisterProductFragmentArgs.fromBundle(requireArguments()))
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(RegisterProductViewModel::class.java)

        val sharedPref = activity?.getSharedPreferences("REGISTER_PRODUCT", Context.MODE_PRIVATE)

        binding.registerProductInquiry.setOnClickListener {
            findNavController().navigate(R.id.action_registerProductFragment_to_conformationFragment)
        }
        binding.registerProductBarcodeButton.setOnClickListener {
            findNavController().navigate(R.id.action_registerProductFragment_to_barcodeFragment)
        }

        binding.registerProductNameInput.apply {
            addTextChangedListener {
                sharedPref!!.edit().putString("registerProductNameInput", it.toString().trim()).apply()
            }
            if (sharedPref != null) {
                setText(sharedPref.getString("registerProductNameInput", null))
            }
        }

        binding.registerProductPhoneInput.apply {
            addTextChangedListener {
                sharedPref!!.edit().putString("registerProductPhoneInput", it.toString().trim()).apply()
            }
            if (sharedPref != null) {
                setText(sharedPref.getString("registerProductPhoneInput", null))
            }
        }

        binding.registerProductBarcodeInput.apply {
            setText(viewModel.args.barcode)
        }

    }


    override fun onDestroyView() {
        fragmentRegisterProductBinding = null
        super.onDestroyView()
    }
}