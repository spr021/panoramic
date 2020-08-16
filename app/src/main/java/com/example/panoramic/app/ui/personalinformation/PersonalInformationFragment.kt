package com.example.panoramic.app.ui.personalinformation

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.panoramic.R
import com.example.panoramic.databinding.FragmentPersonalInformationBinding
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream


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

        viewModel.userInfo?.observe(viewLifecycleOwner, Observer {
            if (it!!.success) {
                binding.loading.visibility = View.GONE
            }
        })

        val cookie =
            activity?.getSharedPreferences("COOKIE", Context.MODE_PRIVATE)!!.getString("COOKIE", "")
        viewModel.getUserInfo(cookie)

        binding.uploadProfilePictuer.setOnClickListener {

                val intent = Intent()
                    .setType("image/*")
                    .setAction(Intent.ACTION_GET_CONTENT)

                startActivityForResult(Intent.createChooser(intent, "Select a file"), 111)
        }

        binding.logout.setOnClickListener {
            viewModel.userInfo?.observe(viewLifecycleOwner, Observer {
                viewModel.logout(it!!.id, cookie!!)
            })
        }
        viewModel.userLogout?.observe(viewLifecycleOwner, Observer {
            if (it) {
                findNavController().navigate(R.id.action_personal_information_to_loginFragment)
            }
        })

        binding.changePassword.setOnClickListener {
            val newFragment = ChangePasswordDialogFragment()
            newFragment.show(requireFragmentManager(), "ChangingPassword")
        }

        binding.transactionHistory.setOnClickListener {
            val newFragment = TransactionHistoryDialogFragment()
            newFragment.show(requireFragmentManager(), "TransactionHistory")
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && requestCode == 111) {
            try {
//                val `is`: InputStream =
//                    requireContext().contentResolver.openInputStream(data?.data!!)!!
                val cookie = activity?.getSharedPreferences("COOKIE", Context.MODE_PRIVATE)!!
                    .getString("COOKIE", "")
                viewModel.uploadImage(cookie!!, data?.data!!, context)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    @Throws(IOException::class)
    fun getBytes(`is`: InputStream): ByteArray? {
        val byteBuff = ByteArrayOutputStream()
        val buffSize = 1024
        val buff = ByteArray(buffSize)
        var len = 0
        while (`is`.read(buff).also { len = it } != -1) {
            byteBuff.write(buff, 0, len)
        }
        return byteBuff.toByteArray()
    }


    override fun onDestroyView() {
        fragmentPersonalInformationBinding = null
        super.onDestroyView()
    }

}