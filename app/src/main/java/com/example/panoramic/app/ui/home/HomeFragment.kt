package com.example.panoramic.app.ui.home

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.panoramic.R
import com.example.panoramic.data.entity.HomeEntity
import com.example.panoramic.databinding.FragmentHomeBinding
import com.example.panoramic.remote.model.CookieResponseDto
import com.example.panoramic.remote.service.CookieService
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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