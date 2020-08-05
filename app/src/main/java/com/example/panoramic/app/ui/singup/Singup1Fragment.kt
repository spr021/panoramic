package com.example.panoramic.app.ui.singup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.panoramic.R
import com.example.panoramic.databinding.FragmentSingup1Binding
import com.wajahatkarim3.easyvalidation.core.collection_ktx.nonEmptyList
import com.wajahatkarim3.easyvalidation.core.view_ktx.minLength
import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail

class Singup1Fragment : Fragment(R.layout.fragment_singup1) {

    private var fragmentSingup1Fragment: FragmentSingup1Binding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSingup1Binding.bind(view)
        fragmentSingup1Fragment = binding

        binding.phoneNumberValue.setText(Singup1FragmentArgs.fromBundle(requireArguments()).phoneNumber)

        binding.nextPage.setOnClickListener {

            var flag: Int = 0

            val name = binding.nameValue
            val nameLayout = binding.name
            name.nonEmpty {
                flag += 1
                nameLayout.error = "انتخاب نام ضروری است"
            }
            name.setOnClickListener {
                nameLayout.error = null
            }
            name.setOnFocusChangeListener { view, b ->
                if (b) {
                    nameLayout.error = null
                }
            }
            val family = binding.familyValue
            val familyLayout = binding.family
            family.nonEmpty {
                flag += 1
                familyLayout.error = "انتخاب فامیلی ضروری است"
            }
            family.setOnClickListener {
                familyLayout.error = null
            }
            val email = binding.emailValue
            val emailLayout = binding.email
            email.nonEmpty {
                flag += 1
                emailLayout.error = "انتخاب ایمیل ضروری است"
            }
            email.validEmail {
                flag += 1
                emailLayout.error = "ایمیل معتبر نیست"
            }
            email.setOnClickListener {
                emailLayout.error = null
            }
            val shopName = binding.shopNameValue
            val shopNameLayout = binding.shopName
            shopName.nonEmpty {
                flag += 1
                shopNameLayout.error = "انتخاب نام فروشگاه ضروری است"
            }
            shopName.setOnClickListener {
                shopNameLayout.error = null
            }
            val shopKeeper = binding.shopOwnerValue
            val shopKeeperLayout = binding.shopOwner
            shopKeeper.nonEmpty {
                flag += 1
                shopKeeperLayout.error = "انتخاب نام صاحب فروشگاه ضروری است"
            }
            shopKeeper.setOnClickListener {
                shopKeeperLayout.error = null
            }
            val shopZip = binding.shopZipValue
            val shopZipLayout = binding.shopZip
            shopZip.nonEmpty {
                flag += 1
                shopZipLayout.error = "انتخاب کد پستی فروشگاه ضروری است"
            }
            shopZip.minLength(10) {
                flag += 1
                shopZipLayout.error = "کد پستی ۱۰ رقم است"
            }
            shopZip.setOnClickListener {
                shopZipLayout.error = null
            }
            shopZipLayout.setOnClickListener {
                shopZipLayout.error = null
            }
            val shopAddress = binding.shopAddressValue
            val shopAddressLayout = binding.shopAddress
            shopAddress.nonEmpty {
                flag += 1
                shopAddressLayout.error = "انتخاب آدرس فروشگاه ضروری است"
            }
            shopAddress.setOnClickListener {
                shopAddressLayout.error = null
            }


            val firstPageInfo = arrayOf(
                name.text.toString().trim(),
                family.text.toString().trim(),
                email.text.toString().trim(),
                shopName.text.toString().trim(),
                shopKeeper.text.toString().trim(),
                shopZip.text.toString().trim(),
                shopAddress.text.toString().trim()
            )

            if (flag == 0) {
                findNavController().navigate(
                    Singup1FragmentDirections.actionSingup1FragmentToSingup2Fragment(
                        firstPageInfo
                    )
                )
            }
        }
    }

    override fun onDestroyView() {
        fragmentSingup1Fragment = null
        super.onDestroyView()
    }

}