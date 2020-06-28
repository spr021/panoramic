package com.example.panoramic.app.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.panoramic.R
import com.example.panoramic.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var fragmentHomeBinding: FragmentHomeBinding? = null
    private lateinit var viewModel: HomeViewModel
    private lateinit var viewModelFactory: HomeViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHomeBinding.bind(view)
        fragmentHomeBinding = binding
        // Initialize the ViewModel
        viewModelFactory = HomeViewModelFactory(HomeFragmentArgs.fromBundle(arguments!!))
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        // to all the data in the ViewModel
        binding.homeViewModel = viewModel

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

        viewModel.confirmSuccess.observe(viewLifecycleOwner, Observer {
            if(it) {
                viewModel.args.observe(viewLifecycleOwner, Observer { args ->
                    Toast.makeText(context, "modelNumber: ${args[0]}, serialNumber: ${args[1]}", Toast.LENGTH_LONG).show()
                    viewModel.onConfirmSuccess()
                })
            }
        })




    }


    override fun onDestroyView() {
        fragmentHomeBinding = null
        super.onDestroyView()
    }
}