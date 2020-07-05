package com.example.panoramic.app.ui.movies.details

import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.panoramic.R

class PlayerFragment : Fragment(R.layout.fragment_player) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //set player LANDSCAPE
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        // hide status bar
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        activity?.actionBar?.hide()

        val args =
            PlayerFragmentArgs.fromBundle(
                arguments!!
            )


        view.findViewById<VideoView>(R.id.video).apply {
            setVideoURI(Uri.parse(args.video))
            start()
            setOnCompletionListener() {
                val seenVideo = true
                view.findNavController().navigate(
                    PlayerFragmentDirections.actionPlayerFragmentToMovieItemFragment(
                        args.title,
                        args.image,
                        args.text,
                        args.video,
                        seenVideo
                    )
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //set player PORTRAIT
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}