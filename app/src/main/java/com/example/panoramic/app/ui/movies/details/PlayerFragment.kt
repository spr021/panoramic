

package com.example.panoramic.app.ui.movies.details

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.panoramic.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.util.Log
import com.google.android.exoplayer2.util.Util
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory as DefaultDataSourceFsubmit_buttonactory1


class PlayerFragment : Fragment(R.layout.fragment_player) {

    private var playbackStateListener: PlaybackStateListener? = null
    private var playerView: PlayerView? = null
    private lateinit var args: PlayerFragmentArgs
    private var player: SimpleExoPlayer? = null
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition: Long = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args = PlayerFragmentArgs.fromBundle(requireArguments())

        playerView = view.findViewById(R.id.video)

        playbackStateListener = PlaybackStateListener()

    }

    override fun onStart() {
        super.onStart()

        if (Util.SDK_INT > 23) {
            initializePlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        hideSystemUi()
        if (Util.SDK_INT <= 23 || player == null) {
            initializePlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }

    @Suppress("DEPRECATION")
    private fun initializePlayer() {
        Log.i("ppplayer", "1")
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        Log.i("ppplayer", "2")
        try {
            if (player == null) {
                val trackSelector = DefaultTrackSelector()
                trackSelector.setParameters(
                    trackSelector.buildUponParameters().setMaxVideoSizeSd()
                )
                player = ExoPlayerFactory.newSimpleInstance(requireContext(), trackSelector)
                playerView?.player = player
                val uri: Uri = Uri.parse(args.video)
                val mediaSource = buildMediaSource(uri)
                player!!.playWhenReady = playWhenReady
                player!!.seekTo(currentWindow, playbackPosition)
                player!!.addListener(this.playbackStateListener!!)
                player!!.prepare(mediaSource, false, false)
            }
        } catch (e: Throwable) {
            Log.i("initializePlayer", e.toString())
        }

    }

    private fun releasePlayer() {
        if (player != null) {
            playbackPosition = player!!.currentPosition
            currentWindow = player!!.currentWindowIndex
            playWhenReady = player!!.playWhenReady
            player!!.removeListener(this.playbackStateListener!!)
            player!!.release()
            player = null
        }
    }

    private fun buildMediaSource(uri: Uri): MediaSource {
        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFsubmit_buttonactory1(
            activity,
            Util.getUserAgent(requireContext(), "Panoramic")
        )
        return ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
    }


    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        try {
            playerView!!.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LOW_PROFILE
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        } catch (e: Throwable){
            Log.i("hideSystemUi", e.toString())
        }

    }

    inner class PlaybackStateListener : Player.EventListener {
        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
            when (playbackState) {
                ExoPlayer.STATE_IDLE -> {
                }
                ExoPlayer.STATE_BUFFERING -> {
                    view?.findViewById<ProgressBar>(R.id.progressBar)?.visibility = View.VISIBLE
                }
                ExoPlayer.STATE_READY -> {
                    view?.findViewById<ProgressBar>(R.id.progressBar)?.visibility = View.GONE
                }
                ExoPlayer.STATE_ENDED -> {
                    activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                    view?.findNavController()
                        ?.navigate(R.id.action_playerFragment_to_moviesFragment)
                    //set player PORTRAIT

                }
                else -> "UNKNOWN_STATE"
            }
        }
    }

    private fun pausePlayer() {
        player!!.playWhenReady = false
        player!!.playbackState
    }

    private fun startPlayer() {
        player!!.playWhenReady = true
        player!!.playbackState
    }

    override fun onDestroy() {
        super.onDestroy()
        //set player PORTRAIT
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}