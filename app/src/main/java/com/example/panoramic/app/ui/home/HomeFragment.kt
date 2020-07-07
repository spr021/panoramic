package com.example.panoramic.app.ui.home

import android.os.Bundle
import android.view.Gravity
import android.view.View
<<<<<<< HEAD
import android.widget.TextView
import android.widget.Toast
=======
import android.widget.ProgressBar
>>>>>>> network
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.panoramic.R
import com.example.panoramic.data.entity.HomeEntity
import com.example.panoramic.databinding.FragmentHomeBinding
<<<<<<< HEAD
import kotlinx.android.synthetic.main.toast_fragment_home.*
=======
import com.example.panoramic.remote.model.CookieResponseDto
import com.example.panoramic.remote.service.CookieService
>>>>>>> network
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
<<<<<<< HEAD

        viewModel.confirmSuccess.observe(viewLifecycleOwner, Observer {
            if(it) {
                viewModel.args.observe(viewLifecycleOwner, Observer { args ->
                    toast(args[0], args[1])
                    viewModel.onConfirmSuccess()
                })
            }
        })


=======
>>>>>>> network


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

<<<<<<< HEAD
=======
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
>>>>>>> network

    override fun onDestroyView() {
        fragmentHomeBinding = null
        super.onDestroyView()
    }
}