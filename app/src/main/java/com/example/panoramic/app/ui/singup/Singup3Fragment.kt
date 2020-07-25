package com.example.panoramic.app.ui.singup

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.panoramic.R
import com.example.panoramic.databinding.FragmentSingup3Binding

class Singup3Fragment : Fragment(R.layout.fragment_singup3) {

    // Scoped to the lifecycle of the fragment's view (between onCreateView and onDestroyView)
    private var fragmentSingup3Binding: FragmentSingup3Binding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSingup3Binding.bind(view)
        fragmentSingup3Binding = binding

        binding.nextPage.setOnClickListener {
            findNavController().navigate(R.id.action_singup3Fragment_to_successSingupFragment)
        }

        //drop down list for bank name
        val bankNameAdapter = ArrayAdapter(requireContext(),
            R.layout.list_item_singup, listOf("بانک تجارت", "بانک ملت", "بانک ملی ایران", "بانک صادرات", "بانک توسعه تعاون", "بانک سپه", "بانک صنعت و معدن", "بانک کشاورزی", "بانک مسکن", "بانک اقتصاد نوین", "بانک پارسیان", "بانک کارآفرین", "بانک سامان", "بانک سینا", "بانک خاورمیانه", "بانک شهر", "بانک دی", "بانک رفاه", "بانک حکمت ایرانیان", "بانک گردشگری", "بانک ایران زمین", "بانک قوامین", "بانک انصار", "بانک سرمایه", "بانک پاسارگاد", "بانک قرض الحسنه مهر ایران", "بانک قرض الحسنه رسالت"))
        (binding.bankNameList as? AutoCompleteTextView)?.setAdapter(bankNameAdapter)

    }

    override fun onDestroyView() {
        // Consider not storing the binding instance in a field, if not needed.
        fragmentSingup3Binding = null
        super.onDestroyView()
    }
}