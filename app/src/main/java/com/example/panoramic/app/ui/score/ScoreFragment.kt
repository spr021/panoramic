package com.example.panoramic.app.ui.score

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.panoramic.R
import com.example.panoramic.data.entity.ScoreEntity
import com.example.panoramic.databinding.FragmentScoreBinding

class ScoreFragment : Fragment(R.layout.fragment_score) {

    private var fragmentScoreBinding: FragmentScoreBinding? = null
    private val viewModel: ScoreViewModel by viewModels()
    lateinit var recyclerViewInformation: ScoreEntity
    private val mNicolasCageMovies = listOf(
        ScoreEntity(
            "PA32BA1633",
            "0139006908248281",
            120,
            "1390/3/12",
            "13:24",
            true
        )
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentScoreBinding.bind(view)
        fragmentScoreBinding = binding

        val viewStateObserver = Observer<ScoreEntity> { viewState ->
            recyclerViewInformation = viewState
        }

        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        binding.scoreRecyclerview.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ScoreAdabter(listOf(recyclerViewInformation))
        }

        viewModel.viewState.observe(viewLifecycleOwner, viewStateObserver)
        viewModel.request()

    }

    override fun onDestroyView() {
        fragmentScoreBinding = null
        super.onDestroyView()
    }



}