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

class Singup2Fragment : Fragment(R.layout.fragment_singup2) {

    // Scoped to the lifecycle of the fragment's view (between onCreateView and onDestroyView)
    private var fragmentSingup2Binding: FragmentSingup2Binding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSingup2Binding.bind(view)
        fragmentSingup2Binding = binding

        //drop down list for marital status
        val maritalStatusAdapter = ArrayAdapter(requireContext(),
            R.layout.list_item_singup, listOf("مجرد", "متاهل"))
        (binding.maritalStatusItem as? AutoCompleteTextView)?.setAdapter(maritalStatusAdapter)

        //drop down list for child number
        val childNumberAdapter = ArrayAdapter(requireContext(),
            R.layout.list_item_singup, listOf("۱", "۲", "۳", "۴", "۵"))
        (binding.childNumberItem as? AutoCompleteTextView)?.setAdapter(childNumberAdapter)

        //drop down list for child number
        val educationAdapter = ArrayAdapter(requireContext(),
            R.layout.list_item_singup, listOf("سیکل", "دیپلم", "کاردانی", "کارشناسی", "کارشناسی ارشد", "سایر"))
        (binding.educationItem as? AutoCompleteTextView)?.setAdapter(educationAdapter)

        val args = Singup2FragmentArgs.fromBundle(requireArguments())

        val nationalId = binding.nationalIdValue.toString()
        val age = binding.ageValue.toString()
        val homeAddress = binding.homeAddressValue.toString()
        val homeZip = binding.homeZipValue.toString()
        val homePhone = binding.homePhoneValue.toString()
        val maritalStatus = binding.maritalStatusItem.toString()
        val childNumber = binding.childNumberItem.toString()
        val education = binding.educationItem.toString()
        val arr = arrayOf(nationalId, age, homeAddress, homeZip, homePhone, maritalStatus, childNumber, education)

        binding.nextPage.setOnClickListener {
            val secondPageInfo = arrayOf(*args.firstPageInfo, *arr)
            Log.i("sasa", secondPageInfo[0])
            findNavController().navigate(R.id.action_singup2Fragment_to_singup3Fragment)
        }
    }

    override fun onDestroyView() {
        // Consider not storing the binding instance in a field, if not needed.
        fragmentSingup2Binding = null
        super.onDestroyView()
    }
}