package com.example.panoramic.app.ui.score

import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.panoramic.R

import com.example.panoramic.databinding.FragmentScoreBinding
import com.example.panoramic.remote.model.Item

class ScoreFragment : Fragment(R.layout.fragment_score) {

    private var fragmentScoreBinding: FragmentScoreBinding? = null
    private val viewModel: ScoreViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentScoreBinding.bind(view)
        fragmentScoreBinding = binding
        binding.scoreViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.progressBar.visibility = View.VISIBLE

        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        val cookie = activity?.getSharedPreferences("COOKIE", Context.MODE_PRIVATE)!!
            .getString("COOKIE", "")
        viewModel.getSellsProduct(cookie)

        viewModel.userInfo.observe(viewLifecycleOwner, Observer {
            if (it.success!!) {
                binding.progressBar.visibility = View.GONE
                binding.scoreRecyclerview.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = ScoreAdabter(it.items!!)
                }
            }
        })

    }

    override fun onDestroyView() {
        fragmentScoreBinding = null
        super.onDestroyView()
    }

}