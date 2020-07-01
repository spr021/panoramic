package com.example.panoramic.app.ui.movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.panoramic.R
import com.example.panoramic.data.entity.MoviesEntity
import com.example.panoramic.databinding.FragmentMoviesBinding

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private val mNicolasCageMovies = listOf(
        MoviesEntity(
            "تفاوت فول اچ دی و اولترا اچ دی چیست؟",
            ":تاریخ انتشار 1399/9/6",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            true
        )
    )

    private var fragmentMoviesBinding: FragmentMoviesBinding? = null
    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var recyclerViewInformation: MoviesEntity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMoviesBinding.bind(view)
        fragmentMoviesBinding = binding
        val viewStateObserver = Observer<MoviesEntity> { viewState ->
            recyclerViewInformation = viewState
        }

        binding.moviesRecyclerview.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = MoviesAdabter(listOf(recyclerViewInformation))
        }

        viewModel.viewState.observe(viewLifecycleOwner, viewStateObserver)
        viewModel.request()

    }

    override fun onDestroyView() {
        fragmentMoviesBinding = null
        super.onDestroyView()
    }
}