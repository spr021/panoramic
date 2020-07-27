package com.example.panoramic.app.ui.singup

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.panoramic.R
import com.example.panoramic.databinding.FragmentSingup2Binding
import com.wajahatkarim3.easyvalidation.core.view_ktx.minLength
import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty

class Singup2Fragment : Fragment(R.layout.fragment_singup2) {

    // Scoped to the lifecycle of the fragment's view (between onCreateView and onDestroyView)
    private var fragmentSingup2Binding: FragmentSingup2Binding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSingup2Binding.bind(view)
        fragmentSingup2Binding = binding

        //drop down list for marital status
        val maritalStatusAdapter = ArrayAdapter(
            requireContext(),
            R.layout.list_item_singup, listOf("مجرد", "متاهل")
        )
        (binding.maritalStatusItem as? AutoCompleteTextView)?.setAdapter(maritalStatusAdapter)

        //drop down list for child number
        val childNumberAdapter = ArrayAdapter(
            requireContext(),
            R.layout.list_item_singup, listOf("0", "1", "2", "3", "4", "5")
        )
        (binding.childNumberItem as? AutoCompleteTextView)?.setAdapter(childNumberAdapter)

        //drop down list for child number
        val educationAdapter = ArrayAdapter(
            requireContext(),
            R.layout.list_item_singup,
            listOf("سیکل", "دیپلم", "کاردانی", "کارشناسی", "کارشناسی ارشد", "سایر")
        )
        (binding.educationItem as? AutoCompleteTextView)?.setAdapter(educationAdapter)

        binding.nextPage.setOnClickListener {

            var flag: Int = 0
            val args = Singup2FragmentArgs.fromBundle(requireArguments())

            val nationalId = binding.nationalIdValue
            val nationalIdLayout = binding.nationalId
            nationalId.nonEmpty {
                flag += 1
                nationalIdLayout.error = "انتخاب کد ملی ضروری است"
            }
            nationalId.setOnClickListener {
                nationalIdLayout.error = null
            }
            val age = binding.ageValue
            val ageLayout = binding.age
            age.nonEmpty {
                flag += 1
                ageLayout.error = "انتخاب سن ضروری است"
            }
            age.setOnClickListener {
                ageLayout.error = null
            }
            val city = binding.cityValue
            val cityLayout = binding.city
            city.nonEmpty {
                flag += 1
                cityLayout.error = "انتخاب شهر ضروری است"
            }
            city.setOnClickListener {
                cityLayout.error = null
            }
            val homeAddress = binding.homeAddressValue
            val homeAddressLayout = binding.homeAddress
            homeAddress.nonEmpty {
                flag += 1
                homeAddressLayout.error = "انتخاب آدرس منزل ضروری است"
            }
            homeAddress.setOnClickListener {
                homeAddressLayout.error = null
            }
            val homePhone = binding.homePhoneValue
            val homePhoneLayout = binding.homePhone
            homePhone.nonEmpty {
                flag += 1
                homePhoneLayout.error = "انتخاب شماره تلفن منزل ضروری است"
            }
            homePhone.setOnClickListener {
                homePhoneLayout.error = null
            }
            val homeZip = binding.homeZipValue
            val homeZipLayout = binding.homeZip
            homeZip.nonEmpty {
                flag += 1
                homeZipLayout.error = "انتخاب کد پستی منزل ضروری است"
            }
            homeZip.setOnClickListener {
                homeZipLayout.error = null
            }
            homeZip.minLength(10) {
                flag += 1
                homeZipLayout.error = "کد پستی ۱۰ رقم است"
            }
            homeZip.setOnClickListener {
                homeZipLayout.error = null
            }
            val maritalStatus = binding.maritalStatusItem
            val maritalStatusLayout = binding.maritalStatus
            val maritalStatusBoolean: String = if (maritalStatus.text.toString() == "متاهل") {
                "1"
            } else {
                "0"
            }
            maritalStatus.nonEmpty {
                flag += 1
                maritalStatusLayout.error = "انتخاب وضعیت تاهل ضروری است"
            }
            maritalStatus.setOnClickListener {
                maritalStatusLayout.error = null
            }
            val childNumber = binding.childNumberItem
            val childNumberLayout = binding.childNumber
            childNumber.nonEmpty {
                flag += 1
                childNumberLayout.error = "انتخاب تعداد فرزند ضروری است"
            }
            childNumber.setOnClickListener {
                childNumberLayout.error = null
            }
            val education = binding.educationItem
            val educationLayout = binding.education
            education.nonEmpty {
                flag += 1
                educationLayout.error = "انتخاب سطح تحصیلات ضروری است"
            }
            education.setOnClickListener {
                educationLayout.error = null
            }
            val arr = arrayOf(
                nationalId.text.toString().trim(),
                age.text.toString().trim(),
                city.text.toString().trim(),
                homeAddress.text.toString().trim(),
                homePhone.text.toString().trim(),
                homeZip.text.toString().trim(),
                maritalStatusBoolean,
                childNumber.text.toString().trim(),
                education.text.toString().trim()
            )

            val secondPageInfo = arrayOf(*args.firstPageInfo, *arr)
            Log.i("sasa", secondPageInfo.contentToString())
            if (flag == 0) {
                findNavController().navigate(
                    Singup2FragmentDirections.actionSingup2FragmentToSingup3Fragment(
                        secondPageInfo
                    )
                )
            }
        }
    }

    override fun onDestroyView() {
        // Consider not storing the binding instance in a field, if not needed.
        fragmentSingup2Binding = null
        super.onDestroyView()
    }
}