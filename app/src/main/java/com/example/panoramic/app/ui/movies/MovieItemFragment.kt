package com.example.panoramic.app.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import com.example.panoramic.R
import com.example.panoramic.app.ui.home.HomeFragmentArgs
import com.example.panoramic.databinding.FragmentMovieItemBinding
import com.squareup.picasso.Picasso

class MovieItemFragment : Fragment(R.layout.fragment_movie_item) {

    private var fragmentMovieItemBinding: FragmentMovieItemBinding? = null
    private var mediaController: MediaController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMovieItemBinding.bind(view)
        fragmentMovieItemBinding = binding

        val args = MovieItemFragmentArgs.fromBundle(arguments!!)

        Picasso.get()
            .load(args.image)
            .placeholder(R.drawable.placeholder_image)
            .into(binding.imageCover)

        binding.title.text = args.title
        binding.text.text = args.text

        binding.play.setOnClickListener {
            binding.video.setVideoPath(args.video)

            mediaController = MediaController(activity)
            mediaController?.setAnchorView(binding.video)
            binding.video.setMediaController(mediaController)
            binding.video.start()
        }


    }

    override fun onDestroyView() {
        fragmentMovieItemBinding = null
        super.onDestroyView()
    }
}