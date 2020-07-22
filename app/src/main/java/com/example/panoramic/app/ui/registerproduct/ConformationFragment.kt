package com.example.panoramic.app.ui.registerproduct

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.panoramic.R
import com.example.panoramic.app.CustomToast
import com.example.panoramic.app.clearSharedPreferences
import com.example.panoramic.app.isOnline
import com.example.panoramic.app.ui.home.HomeViewModel
import com.example.panoramic.app.ui.home.HomeViewModelFactory
import com.example.panoramic.databinding.FragmentConformationBinding

class ConformationFragment : Fragment(R.layout.fragment_conformation) {

    private var fragmentConformationBinding: FragmentConformationBinding? = null
    private lateinit var viewModel: ConformationViewModel
    private lateinit var viewModelFactory: ConformationViewModelFactory

    private var confirmSuccess: Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentConformationBinding.bind(view)
        fragmentConformationBinding = binding

        viewModelFactory =
            ConformationViewModelFactory(ConformationFragmentArgs.fromBundle(requireArguments()))
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ConformationViewModel::class.java)

        //set response to text views
        binding.modelValue.text = viewModel.args.modelNumber
        binding.sizeValue.text = viewModel.args.size
        binding.typeValue.text = viewModel.args.type
        binding.resolotionValue.text = viewModel.args.resolotion
        binding.serialValue.text = viewModel.args.serialNumber
        binding.panelValue.text = viewModel.args.panel

        //go to edite information
        binding.editingInformationButton.setOnClickListener {
            clearSharedPreferences(Activity(), "REGISTER_PRODUCT")
            findNavController().navigate(R.id.action_conformationFragment_to_registerProductFragment)
        }
        //click for register product
        binding.confirmButton.setOnClickListener {
            val cookie = activity?.getSharedPreferences("COOKIE", Context.MODE_PRIVATE)!!
                .getString("COOKIE", "")
                viewModel.confirmProduct(cookie)
            viewModel.confirmationProductResponse?.observe(viewLifecycleOwner, Observer {
                Log.i("qqq", it.toString())
                if (it.success) {
                    activity?.getSharedPreferences("REGISTER_PRODUCT", Context.MODE_PRIVATE)!!.edit().clear().apply()
                    findNavController().navigate(
                        ConformationFragmentDirections.actionConformationFragmentToHomeFragment(
                            viewModel.args.modelNumber,
                            viewModel.args.serialNumber,
                            confirmSuccess
                        )
                    )
                } else {
                    CustomToast(this.requireActivity(), "مشکل در ارسال درخواست", R.color.red)
                }
            })
        }

    }

    override fun onDestroyView() {
        fragmentConformationBinding = null
        super.onDestroyView()
    }
}