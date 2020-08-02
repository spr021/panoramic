package com.example.panoramic.app.ui.forgetpassword

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.panoramic.R
import com.example.panoramic.app.ui.forgetpassword.viewmodel.ForgetpasswordSMSViewModel
import com.example.panoramic.app.ui.forgetpassword.viewmodel.NewPasswordViewModel
import com.example.panoramic.databinding.FragmentForgetpasswordSmsBinding
import com.example.panoramic.databinding.FragmentNewPasswordBinding
import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty

class NewPasswordFragment : Fragment(R.layout.fragment_new_password) {

    private var fragmentNewPasswordBinding: FragmentNewPasswordBinding? = null
    private lateinit var viewModel: NewPasswordViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentNewPasswordBinding.bind(view)
        fragmentNewPasswordBinding = binding
        viewModel = ViewModelProviders.of(this).get(NewPasswordViewModel::class.java)

        val sharedPref = activity?.getSharedPreferences("COOKIE", Context.MODE_PRIVATE)
        val cookie = sharedPref!!.getString("COOKIE", "")
        binding.newPasswordButton.setOnClickListener {
            val password = binding.newPassword.text.toString().trim()
            val renewPassword = binding.renewPassword.text.toString().trim()
            if (password == renewPassword) {
                if (password.nonEmpty() && renewPassword.nonEmpty()) {
                    Log.i("www", "1")
                    viewModel.sendNewPassword(password, cookie)
                } else {
                    val errorText = binding.message
                    errorText.text = "فیلد هارا پر کنید"
                    errorText.visibility = View.VISIBLE
                }
            } else {
                val errorText = binding.message
                errorText.text = "رمز عبور جدید با تکرار رمز عبور یکی نیست"
                errorText.visibility = View.VISIBLE
            }
        }
        viewModel.requestResponse.observe(viewLifecycleOwner, Observer {
            if (it) {
                Log.i("www", "2")
                findNavController().navigate(R.id.action_newPasswordFragment_to_loginFragment)
            }
        })
    }
}