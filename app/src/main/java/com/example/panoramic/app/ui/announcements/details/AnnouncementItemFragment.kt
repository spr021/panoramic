package com.example.panoramic.app.ui.announcements.details

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.panoramic.R
import com.example.panoramic.databinding.FragmentAnnouncementItemBinding


class AnnouncementItemFragment : Fragment(R.layout.fragment_announcement_item) {

    private var fragmentAnnouncementItemBinding: FragmentAnnouncementItemBinding? = null
    private lateinit var viewModel: AnnouncementItemViewModel
    private lateinit var viewModelFactory: AnnouncementItemViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelFactory = AnnouncementItemViewModelFactory(
            AnnouncementItemFragmentArgs.fromBundle(requireArguments())
        )
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(AnnouncementItemViewModel::class.java)

        val binding = FragmentAnnouncementItemBinding.bind(view)
        fragmentAnnouncementItemBinding = binding
        binding.announcementViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        val cookie =
            activity?.getSharedPreferences("COOKIE", Context.MODE_PRIVATE)!!.getString("COOKIE", "")
        viewModel.seenAnnouncements(cookie, viewModel.args.id)
    }

    override fun onDestroyView() {
        fragmentAnnouncementItemBinding = null
        super.onDestroyView()
    }
}