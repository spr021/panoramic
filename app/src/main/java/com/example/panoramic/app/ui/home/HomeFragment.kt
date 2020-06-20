package com.example.panoramic.app.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.panoramic.R
import com.example.panoramic.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var fragmentHomeBinding: FragmentHomeBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        fragmentHomeBinding = binding
        binding.yourScore.setOnClickListener {
            val extras = FragmentNavigatorExtras(your_score to "your_score")
            findNavController().navigate(R.id.action_homeFragment_to_scoreFragment, null, null, extras)
        }
        binding.registerNewProduct.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_registerProductFragment)
        }
        binding.educationalVideos.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_moviesFragment)
        }
        binding.announcements.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_announcementsFragment)
        }
        //val args = HomeFragmentArgs.fromBundle(arguments!!)
        //Toast.makeText(context, "NumCorrect: ${args.modelNumber}, NumQuestions: ${args.serialNumber}", Toast.LENGTH_LONG).show()


    }

    override fun onDestroyView() {
        fragmentHomeBinding = null
        super.onDestroyView()
    }
}