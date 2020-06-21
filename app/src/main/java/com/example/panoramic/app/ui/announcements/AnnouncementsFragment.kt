package com.example.panoramic.app.ui.announcements

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.panoramic.R
import com.example.panoramic.data.entity.AnnouncementsEntity
import com.example.panoramic.databinding.FragmentAnnouncementsBinding

class AnnouncementsFragment : Fragment(R.layout.fragment_announcements) {

    private var fragmentAnnouncementsBinding: FragmentAnnouncementsBinding? = null
    private val viewModel: AnnouncementsViewModel by viewModels()
    lateinit var recyclerViewInformation: AnnouncementsEntity

    private val mNicolasCageMovies = listOf(
        AnnouncementsEntity(
            "تفاوت فول اچ دی و اولترا اچ دی چیست؟",
            ":تاریخ انتشار 1399/9/6",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            true
        )
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAnnouncementsBinding.bind(view)
        fragmentAnnouncementsBinding = binding
        val viewStateObserver = Observer<AnnouncementsEntity> { viewState ->
            recyclerViewInformation = viewState
        }

        binding.announcemntsRecyclerview.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = AnnouncementsAdabter(listOf(recyclerViewInformation))
        }

        viewModel.viewState.observe(viewLifecycleOwner, viewStateObserver)
        viewModel.request()

    }

    override fun onDestroyView() {
        fragmentAnnouncementsBinding = null
        super.onDestroyView()
    }
}