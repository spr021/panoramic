package com.example.panoramic.app.ui.singup

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.panoramic.R
import com.example.panoramic.app.CustomToast
import com.example.panoramic.app.ui.singup.viewmodel.SMSViewModel
import com.example.panoramic.databinding.FragmentSmsBinding

class SMSFragment : Fragment(R.layout.fragment_sms) {

    private var fragmentSMSBinding: FragmentSmsBinding? = null
    private lateinit var viewModel: SMSViewModel
    private var SMS: String? = null

    @Suppress("DEPRECATION")
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSmsBinding.bind(view)
        fragmentSMSBinding = binding
        viewModel = ViewModelProviders.of(this).get(SMSViewModel::class.java)

        val args = SMSFragmentArgs.fromBundle(requireArguments())

        val cookie = activity?.getSharedPreferences("COOKIE", Context.MODE_PRIVATE)!!
            .getString("COOKIE", "")
        viewModel.timer.start()
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
                        newFragment.show(childFragmentManager, "ConfirmPhoneNumber")
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
            viewModel.onInputFillFinish(SMS.toString(), cookie)
        }
        viewModel.requestResponse.observe(viewLifecycleOwner, Observer {
            if (it) {
                findNavController().navigate(SMSFragmentDirections.actionSMSFragmentToSingup1Fragment(args.phoneNumber))
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
        viewModel.requestResponseResend.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.phoneButton.isEnabled = true
                binding.phoneButton.setBackgroundResource(R.drawable.login_button)
            }
        })
    }

    private fun clearInputFocus(binding: FragmentSmsBinding, numberInput: Int) {
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
        fragmentSMSBinding = null
        super.onDestroyView()
    }

    fun onDismiss() {
        view?.findViewById<TextView>(R.id.resend)!!.visibility = View.GONE
        view?.findViewById<TextView>(R.id.timer)!!.visibility = View.VISIBLE
        viewModel.timer.cancel()
        viewModel.timer.start()
    }
}