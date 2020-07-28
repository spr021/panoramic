package com.example.panoramic.app.ui.home

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.panoramic.R
import com.example.panoramic.app.CustomToast
import com.example.panoramic.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

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
        binding.lifecycleOwner = viewLifecycleOwner

        setWellcomeText()

        val cookie =
            activity?.getSharedPreferences("COOKIE", Context.MODE_PRIVATE)!!.getString("COOKIE", "")
        viewModel.getUserInfo(cookie)

        activity?.getSharedPreferences("REGISTER_PRODUCT", Context.MODE_PRIVATE)!!.edit().clear()
            .apply()

        binding.yourScore.setOnClickListener {
            val extras = FragmentNavigatorExtras(your_score to "your_score")
            findNavController().navigate(
                R.id.action_homeFragment_to_scoreFragment,
                null,
                null,
                extras
            )
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
            if (it) {
                viewModel.args.observe(viewLifecycleOwner, Observer { args ->
                    val modelNumber = args[0]
                    val serialNumber = args[1]

                    CustomToast(
                        requireActivity(),
                        " دستگاه $modelNumber به شماره سریال $serialNumber برای شما ثبت شد و پس از تایید خدمات پس از فروش، امتیاز آن به حساب شما اعمال خواهد شد ",
                        R.color.green
                    )

                    viewModel.onConfirmSuccess()
                })
            }
        })

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


    private fun setWellcomeText() {
        val rightNow = Calendar.getInstance()
        val currentHourIn24Format = rightNow[Calendar.HOUR_OF_DAY]
        val wellcomeText = view?.findViewById<TextView>(R.id.welcome_text)
        when (currentHourIn24Format) {
            in 5..10 -> wellcomeText!!.text = "صبح به خیر"
            in 10..15 -> wellcomeText!!.text = "ظهر به خیر"
            in 15..19 -> wellcomeText!!.text = "عصر به خیر"
            else -> wellcomeText!!.text = "شب به خیر"
        }
    }

    override fun onDestroyView() {
        fragmentHomeBinding = null
        super.onDestroyView()
    }


}