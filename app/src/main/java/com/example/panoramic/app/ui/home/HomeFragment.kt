package com.example.panoramic.app.ui.home

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.panoramic.R
import com.example.panoramic.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.toast_fragment_home.*
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
        viewModelFactory = HomeViewModelFactory(HomeFragmentArgs.fromBundle(requireArguments()))
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        // to all the data in the ViewModel
        binding.homeViewModel = viewModel

        activity?.getSharedPreferences("REGISTER_PRODUCT", Context.MODE_PRIVATE)!!.edit().clear().apply()

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
                    toast(args[0], args[1])
                    viewModel.onConfirmSuccess()
                })
            }
        })

    }

    private fun toast(modelNumber: String?, serialNumber: String?) {
        val toastText = " دستگاه $modelNumber به شماره سریال $serialNumber برای شما ثبت شد و پس از تایید خدمات پس از فروش، امتیاز آن به حساب شما اعمال خواهد شد "
        val layout = layoutInflater.inflate(R.layout.toast_fragment_home,linearLayout)
            val myToast = Toast(activity)
            val textview = layout.findViewById<TextView>(R.id.custom_toast_message)
            textview.text = toastText
            myToast.duration = Toast.LENGTH_LONG
            myToast.setGravity(Gravity.FILL_HORIZONTAL or Gravity.TOP, 0, 0)
            myToast.view = layout
            myToast.show()
    }

    /*private fun onUserInfoReady(list: List<HomeEntity>?) {
        activity?.runOnUiThread {

            //val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
            val progressBar = view?.findViewById<ProgressBar>(R.id.progressBar)
            progressBar?.visibility = View.GONE

            //adapter = ModernHomeAdapter(list ?: listOf(), this)
            //recyclerView?.adapter = adapter

            //val categoryRepository = Injector.provideCategoryRepository()
            //categoryRepository.getCategories(::onCategoriesReady)
        }
    }*/

    override fun onDestroyView() {
        fragmentHomeBinding = null
        super.onDestroyView()
    }
}