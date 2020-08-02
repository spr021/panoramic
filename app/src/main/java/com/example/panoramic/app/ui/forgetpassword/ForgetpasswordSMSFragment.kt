package com.example.panoramic.app.ui.forgetpassword

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.panoramic.R
import com.example.panoramic.app.CustomToast
import com.example.panoramic.app.ui.forgetpassword.viewmodel.ForgetpasswordSMSViewModel
import com.example.panoramic.databinding.FragmentForgetpasswordSmsBinding

class ForgetpasswordSMSFragment : Fragment(R.layout.fragment_forgetpassword_sms) {

    private var fragmentForgetPasswordSMSBinding: FragmentForgetpasswordSmsBinding? = null
    private lateinit var viewModel: ForgetpasswordSMSViewModel
    private var SMS: String? = null

    @Suppress("DEPRECATION")
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentForgetpasswordSmsBinding.bind(view)
        fragmentForgetPasswordSMSBinding = binding
        viewModel = ViewModelProviders.of(this).get(ForgetpasswordSMSViewModel::class.java)

        val args = ForgetpasswordSMSFragmentArgs.fromBundle(requireArguments())

        val cookie = activity?.getSharedPreferences("COOKIE", Context.MODE_PRIVATE)!!
            .getString("COOKIE", "")

        viewModel.currentTimeString.observe(viewLifecycleOwner, Observer {
            binding.timer.text = "$it تا ارسال مجدد کد "
            if (it == "00:00") {
                binding.timer.visibility = View.GONE
                binding.resend.apply {
                    visibility = View.VISIBLE
                    setOnClickListener {
                        binding.phoneButton.isEnabled = true
                        binding.phoneButton.setBackgroundResource(R.drawable.login_button)
                        val newFragment = ConfirmPhoneNumberDialog()
                        val phoneNumber = Bundle()
                        phoneNumber.putString("PhoneNumber", args.phoneNumber);
                        newFragment.arguments = phoneNumber
                        newFragment.show(requireFragmentManager(), "ConfirmPhoneNumber")

                    }
                }
            }
        })

        binding.smsInput1.requestFocus()

        binding.smsInput1.apply {
            addTextChangedListener {
                if (it.toString().trim().length == 1) {
                    SMS = binding.smsInput1.text.toString()
                    binding.smsInput2.requestFocus()

                }
            }
            setOnClickListener {
                clearInputFocus(binding, 1)
            }
        }
        binding.smsInput2.apply {
            addTextChangedListener {
                if (it.toString().trim().length == 1) {
                    SMS += binding.smsInput2.text
                    binding.smsInput3.requestFocus()
                }
            }
            setOnClickListener {
                clearInputFocus(binding, 2)
            }
        }
        binding.smsInput3.apply {
            addTextChangedListener {
                if (it.toString().trim().length == 1) {
                    SMS += binding.smsInput3.text
                    binding.smsInput4.requestFocus()
                }
            }
            setOnClickListener {
                clearInputFocus(binding, 3)
            }

        }
        binding.smsInput4.apply {
            addTextChangedListener {
                if (binding.smsInput4.text.toString().trim().length == 1) {
                    SMS += binding.smsInput4.text
                    binding.smsInput5.requestFocus()
                }
            }
            setOnClickListener {
                clearInputFocus(binding, 4)
            }
        }
        binding.smsInput5.apply {
            addTextChangedListener {
                if (binding.smsInput5.text.toString().trim().length == 1) {
                    SMS += binding.smsInput5.text
                    binding.phoneButton.requestFocus()
                }
            }
            setOnClickListener {
                clearInputFocus(binding, 5)
            }
        }

        binding.phoneButton.setOnClickListener {
            Log.i("sms", SMS.toString())
            viewModel.onInputFillFinish(SMS.toString(), cookie)
        }
        viewModel.requestResponse.observe(viewLifecycleOwner, Observer {
            if (it) {
                findNavController().navigate(R.id.action_forgetpasswordSMSFragment_to_newPasswordFragment)
            } else {
                CustomToast(this.requireActivity(), "کد وارد شده اشتباه است", R.color.red)
                binding.phoneButton.isEnabled = false
                binding.phoneButton.setBackgroundResource(R.drawable.login_button_disable)
                binding.smsInput1.text = null
                binding.smsInput2.text = null
                binding.smsInput3.text = null
                binding.smsInput4.text = null
                binding.smsInput5.text = null
            }
        })
    }

    private fun clearInputFocus(binding: FragmentForgetpasswordSmsBinding, numberInput: Int) {
        val smsInput = when (numberInput) {
            1 -> binding.smsInput1
            2 -> binding.smsInput2
            3 -> binding.smsInput3
            4 -> binding.smsInput4
            else -> binding.smsInput5
        }
        smsInput.clearFocus()
        binding.smsInput1.requestFocus()
        binding.smsInput1.text = null
        binding.smsInput2.text = null
        binding.smsInput3.text = null
        binding.smsInput4.text = null
        binding.smsInput5.text = null
    }

    override fun onDestroyView() {
        fragmentForgetPasswordSMSBinding = null
        super.onDestroyView()
    }
}