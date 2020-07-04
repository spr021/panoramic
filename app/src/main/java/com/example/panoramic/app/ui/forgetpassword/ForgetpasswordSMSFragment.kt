package com.example.panoramic.app.ui.forgetpassword

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.panoramic.R
import com.example.panoramic.app.ui.singup.viewmodel.ForgetpasswordSMSViewModel
import com.example.panoramic.databinding.FragmentForgetpasswordSmsBinding

class ForgetpasswordSMSFragment : Fragment(R.layout.fragment_forgetpassword_sms) {

    private var fragmentforgetpasswordSMSBinding: FragmentForgetpasswordSmsBinding? = null
    private lateinit var viewModel: ForgetpasswordSMSViewModel
    var SMS: String? = null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentForgetpasswordSmsBinding.bind(view)
        fragmentforgetpasswordSMSBinding = binding
        viewModel = ViewModelProviders.of(this).get(ForgetpasswordSMSViewModel::class.java)

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
                binding.smsInput2.clearFocus()
                binding.smsInput1.requestFocus()
                binding.smsInput1.text = null
                binding.smsInput2.text = null
                binding.smsInput3.text = null
                binding.smsInput4.text = null
                binding.smsInput5.text = null
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
                binding.smsInput3.clearFocus()
                binding.smsInput1.requestFocus()
                binding.smsInput1.text = null
                binding.smsInput2.text = null
                binding.smsInput3.text = null
                binding.smsInput4.text = null
                binding.smsInput5.text = null
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
                binding.smsInput4.clearFocus()
                binding.smsInput1.requestFocus()
                binding.smsInput1.text = null
                binding.smsInput2.text = null
                binding.smsInput3.text = null
                binding.smsInput4.text = null
                binding.smsInput5.text = null
            }
        }
        binding.smsInput5.apply {
            addTextChangedListener {
                if(binding.smsInput5.text.toString().trim().length == 1) {
                    SMS += binding.smsInput5.text
                    binding.smsInput5.clearFocus()
                    binding.phoneButton.requestFocus()
                    viewModel.onInputFillFinish(SMS.toString())
                }
            }
            setOnClickListener {
                binding.smsInput5.clearFocus()
                binding.smsInput1.requestFocus()
                binding.smsInput1.text = null
                binding.smsInput2.text = null
                binding.smsInput3.text = null
                binding.smsInput4.text = null
                binding.smsInput5.text = null
            }
        }

    }

    override fun onDestroyView() {
        fragmentforgetpasswordSMSBinding = null
        super.onDestroyView()
    }
}