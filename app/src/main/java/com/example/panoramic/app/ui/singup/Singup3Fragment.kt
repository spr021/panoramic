package com.example.panoramic.app.ui.singup

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.panoramic.R
import com.example.panoramic.app.ui.singup.viewmodel.Singup3ViewModel
import com.example.panoramic.databinding.FragmentSingup3Binding
import com.wajahatkarim3.easyvalidation.core.view_ktx.minLength
import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty

class Singup3Fragment : Fragment(R.layout.fragment_singup3) {

    // Scoped to the lifecycle of the fragment's view (between onCreateView and onDestroyView)
    private var fragmentSingup3Binding: FragmentSingup3Binding? = null
    private lateinit var viewModel: Singup3ViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSingup3Binding.bind(view)
        fragmentSingup3Binding = binding
        viewModel = ViewModelProviders.of(this).get(Singup3ViewModel::class.java)



        binding.nextPage.setOnClickListener {

            var flag: Int = 0

            val args = Singup3FragmentArgs.fromBundle(requireArguments())
            val cookie =
                activity?.getSharedPreferences("COOKIE", Context.MODE_PRIVATE)!!
                    .getString("COOKIE", "")

            val cardNumber = binding.cardNumberValue
            val cardNumberLayout = binding.cardNumber
            cardNumber.nonEmpty {
                flag += 1
                cardNumberLayout.error = "انتخاب شماره کارت ضروری است"
            }
            cardNumber.minLength(16) {
                flag += 1
                cardNumberLayout.error = "شماره کارت ۱۶ رقم است"
            }
            cardNumber.setOnClickListener {
                cardNumberLayout.error = null
            }
            val accountNumber = binding.accountNumberValue
            val accountNumberLayout = binding.accountNumber
            accountNumber.nonEmpty {
                flag += 1
                accountNumberLayout.error = "انتخاب شماره حساب ضروری است"
            }
            accountNumber.minLength(10) {
                flag += 1
                accountNumberLayout.error = "شماره حساب ۱۰ رقم است"
            }
            accountNumber.setOnClickListener {
                accountNumberLayout.error = null
            }
            val shaba = binding.shabaNumberValue
            val shabaLayout = binding.shabaNumber
            shaba.nonEmpty {
                flag += 1
                shabaLayout.error = "انتخاب شماره شبا ضروری است"
            }
            shaba.minLength(24) {
                flag += 1
                shabaLayout.error = "شماره شبا ۲۴ رقم است"
            }
            shaba.setOnClickListener {
                shabaLayout.error = null
            }
            val bankName = binding.bankNameList
            val bankNameLayout = binding.bankName
            bankName.nonEmpty {
                flag += 1
                bankNameLayout.error = "انتخاب بانک ضروری است"
            }
            bankName.setOnClickListener {
                bankNameLayout.error = null
            }
            val arr = arrayOf(
                cardNumber.text.toString().trim(),
                accountNumber.text.toString().trim(),
                shaba.text.toString().trim(),
                bankName.text.toString().trim()
            )

            val singUpInfo = arrayOf(cookie, *args.secondPageInfo, *arr)
            if (flag == 0) {
                viewModel.singUp(singUpInfo)
            }
        }
        viewModel.requestResponse.observe(viewLifecycleOwner, Observer {
            Log.i("responce", viewModel.requestResponse.toString())
            if (it) {
                Log.i("responce", viewModel.requestResponse.toString())
                findNavController().navigate(R.id.action_singup3Fragment_to_successSingupFragment)
            }
        })

        //drop down list for bank name
        val bankNameAdapter = ArrayAdapter(
            requireContext(),
            R.layout.list_item_singup,
            listOf(
                "بانک تجارت",
                "بانک ملت",
                "بانک ملی ایران",
                "بانک صادرات",
                "بانک توسعه تعاون",
                "بانک سپه",
                "بانک صنعت و معدن",
                "بانک کشاورزی",
                "بانک مسکن",
                "بانک اقتصاد نوین",
                "بانک پارسیان",
                "بانک کارآفرین",
                "بانک سامان",
                "بانک سینا",
                "بانک خاورمیانه",
                "بانک شهر",
                "بانک دی",
                "بانک رفاه",
                "بانک حکمت ایرانیان",
                "بانک گردشگری",
                "بانک ایران زمین",
                "بانک قوامین",
                "بانک انصار",
                "بانک سرمایه",
                "بانک پاسارگاد",
                "بانک قرض الحسنه مهر ایران",
                "بانک قرض الحسنه رسالت"
            )
        )
        (binding.bankNameList as? AutoCompleteTextView)?.setAdapter(bankNameAdapter)

    }

    override fun onDestroyView() {
        // Consider not storing the binding instance in a field, if not needed.
        fragmentSingup3Binding = null
        super.onDestroyView()
    }
}