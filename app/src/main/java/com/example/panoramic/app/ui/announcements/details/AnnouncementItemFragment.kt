package com.example.panoramic.app.ui.announcements.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.panoramic.R
import com.example.panoramic.databinding.FragmentAnnouncementItemBinding
import com.squareup.picasso.Picasso

class AnnouncementItemFragment : Fragment(R.layout.fragment_announcement_item) {

    private var fragmentAnnouncementItemBinding: FragmentAnnouncementItemBinding? = null
    private lateinit var viewModel: AnnouncementItemViewModel
    private lateinit var viewModelFactory: AnnouncementItemViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelFactory = AnnouncementItemViewModelFactory(AnnouncementItemFragmentArgs.fromBundle(requireArguments()))
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AnnouncementItemViewModel::class.java)

        val binding = FragmentAnnouncementItemBinding.bind(view)
        fragmentAnnouncementItemBinding = binding


        Picasso.get()
            .load(viewModel.args.image)
            .placeholder(R.drawable.placeholder_image)
            .into(binding.imageCover)

        binding.title.text = viewModel.args.title
        binding.text.text = viewModel.args.text
    }


    override fun onDestroyView() {
        fragmentAnnouncementItemBinding = null
        super.onDestroyView()
    }
}