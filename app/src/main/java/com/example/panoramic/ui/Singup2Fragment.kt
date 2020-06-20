package com.example.panoramic.ui

import android.os.Bundle
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

        binding.nextPage.setOnClickListener {
            findNavController().navigate(R.id.action_singup2Fragment_to_singup3Fragment, null, null, null)
        }
    }

    override fun onDestroyView() {
        // Consider not storing the binding instance in a field, if not needed.
        fragmentSingup2Binding = null
        super.onDestroyView()
    }
}