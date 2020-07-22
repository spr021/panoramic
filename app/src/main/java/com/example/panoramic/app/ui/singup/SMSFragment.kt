package com.example.panoramic.app.ui.singup

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.panoramic.R
import com.example.panoramic.app.CustomToast
import com.example.panoramic.app.ui.singup.viewmodel.SMSViewModel
import com.example.panoramic.databinding.FragmentForgetpasswordSmsBinding
import com.example.panoramic.databinding.FragmentSmsBinding

class SMSFragment : Fragment(R.layout.fragment_sms) {

    private var fragmentSMSBinding: FragmentSmsBinding? = null
    private lateinit var viewModel: SMSViewModel
    var SMS: String? = null

    @SuppressLint("SetTextI18n")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSmsBinding.bind(view)
        fragmentSMSBinding = binding
        viewModel = ViewModelProviders.of(this).get(SMSViewModel::class.java)

        viewModel.currentTimeString.observe(viewLifecycleOwner, Observer {
            binding.timer.text = "$it تا ارسال مجدد کد "
            if(it == "00:00"){
                binding.timer.visibility = View.GONE
                binding.resend.apply {
                    visibility = View.VISIBLE
                    setOnClickListener {
                        viewModel.onResendCodeClick()
                    }
                }
            }
        })

        binding.smsInput1.addTextChangedListener {
            if(binding.smsInput1.text.toString().trim().length == 1) {
                SMS = binding.smsInput1.text.toString()
                binding.smsInput1.clearFocus()
                binding.smsInput2.requestFocus()
            }
        }
        binding.smsInput2.apply {
            addTextChangedListener {
                if(binding.smsInput2.text.toString().trim().length == 1) {
                    SMS += binding.smsInput2.text
                    binding.smsInput2.clearFocus()
                    binding.smsInput3.requestFocus()
                }
            }
            setOnClickListener {
                clearInputFocus(binding, 2)
            }
        }
        binding.smsInput3.apply {
            addTextChangedListener {
                if(binding.smsInput3.text.toString().trim().length == 1) {
                    SMS += binding.smsInput3.text
                    binding.smsInput3.clearFocus()
                    binding.smsInput4.requestFocus()
                }
            }
            setOnClickListener {
                clearInputFocus(binding, 3)
            }

        }
        binding.smsInput4.apply {
            addTextChangedListener {
                if(binding.smsInput4.text.toString().trim().length == 1) {
                    SMS += binding.smsInput4.text
                    binding.smsInput4.clearFocus()
                    binding.smsInput5.requestFocus()
                }
            }
            setOnClickListener {
                clearInputFocus(binding, 4)
            }
        }
        binding.smsInput5.apply {
            addTextChangedListener {
                if(binding.smsInput5.text.toString().trim().length == 1) {
                    SMS += binding.smsInput5.text
                    binding.smsInput5.clearFocus()
                    binding.phoneButton.requestFocus()
                    val cookie = activity?.getSharedPreferences("COOKIE", Context.MODE_PRIVATE)!!
                        .getString("COOKIE", "")
                    viewModel.onInputFillFinish(SMS.toString(), cookie)
                }
            }
            setOnClickListener {
                clearInputFocus(binding, 5)
            }
        }

        binding.phoneButton.setOnClickListener {
            val cookie = activity?.getSharedPreferences("COOKIE", Context.MODE_PRIVATE)!!
                .getString("COOKIE", "")
            viewModel.onInputFillFinish(SMS.toString(), cookie)
        }
        viewModel.requestResponse.observe(viewLifecycleOwner, Observer {
            if(it) {
                findNavController().navigate(R.id.action_SMSFragment_to_singup1Fragment)
            } else {
                CustomToast(this.requireActivity(), "کد وارد شده اشتباه است", R.color.red)
            }
        })
    }

    private fun clearInputFocus(binding: FragmentSmsBinding, numberInput: Int) {
        val smsInput = when(numberInput) {
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
        fragmentSMSBinding = null
        super.onDestroyView()
    }
}