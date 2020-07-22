package com.example.panoramic.app.ui.personalinformation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.panoramic.R
import com.example.panoramic.databinding.FragmentPersonalInformationBinding


@Suppress("DEPRECATION")
class PersonalInformationFragment : Fragment(R.layout.fragment_personal_information) {

    private var fragmentPersonalInformationBinding: FragmentPersonalInformationBinding? = null
    private lateinit var viewModel: PersonalInformationViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentPersonalInformationBinding.bind(view)
        fragmentPersonalInformationBinding = binding

        viewModel = ViewModelProviders.of(this).get(PersonalInformationViewModel::class.java)

        binding.personalViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val cookie = activity?.getSharedPreferences("COOKIE", Context.MODE_PRIVATE)!!.getString("COOKIE", "")
        viewModel.getUserInfo(cookie)

        binding.changePassword.setOnClickListener {
            val newFragment = ChangePasswordDialogFragment()
            newFragment.show(requireFragmentManager(), "ChangingPassword")
        }

        binding.transactionHistory.setOnClickListener {
            val newFragment = TransactionHistoryDialogFragment()
            newFragment.show(requireFragmentManager(), "TransactionHistory")
        }

    }

    override fun onDestroyView() {
        fragmentPersonalInformationBinding = null
        super.onDestroyView()
    }

}