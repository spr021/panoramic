package com.example.panoramic.app.ui.announcements

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.panoramic.R
import com.example.panoramic.data.entity.AnnouncementsEntity
import com.example.panoramic.databinding.FragmentAnnouncementsBinding

class AnnouncementsFragment : Fragment(R.layout.fragment_announcements) {

    private var fragmentAnnouncementsBinding: FragmentAnnouncementsBinding? = null

    private val mNicolasCageMovies = listOf(
        AnnouncementsEntity(
            "تفاوت فول اچ دی و اولترا اچ دی چیست؟",
            ":تاریخ انتشار 1399/9/6",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            true
        ),
        AnnouncementsEntity(
            "تکنولوژی اچ در آر چیست؟",
            ":تاریخ انتشار 1320/3/9",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            false
        ),
        AnnouncementsEntity(
            "چرا اسمارت؟",
            ":تاریخ انتشار 1990/3/9",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            true
        ),
        AnnouncementsEntity(
            "چگونه گوشی را به تلویزیون وصل کنیم",
            ":تاریخ انتشار 1399/3/9",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            true
        ),
        AnnouncementsEntity(
            "کیولد چگونه کار میکند؟",
            ":تاریخ انتشار 1390/2/9",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            false
        ),
        AnnouncementsEntity(
            "اندروید تیوی چیست؟",
            ":تاریخ انتشار 1390/3/12",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            true
        ),
        AnnouncementsEntity(
            "تفاوت فول اچ دی و اولترا اچ دی چیست؟",
            ":تاریخ انتشار 1399/9/6",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            false
        ),
        AnnouncementsEntity(
            "تکنولوژی اچ در آر چیست؟",
            ":تاریخ انتشار 1320/3/9",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            false
        ),
        AnnouncementsEntity(
            "چرا اسمارت؟",
            ":تاریخ انتشار 1990/3/9",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            true
        ),
        AnnouncementsEntity(
            "چگونه گوشی را به تلویزیون وصل کنیم",
            ":تاریخ انتشار 1399/3/9",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            true
        ),
        AnnouncementsEntity(
            "کیولد چگونه کار میکند؟",
            ":تاریخ انتشار 1390/2/9",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            false
        ),
        AnnouncementsEntity(
            "اندروید تیوی چیست؟",
            ":تاریخ انتشار 1390/3/12",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            false
        ),
        AnnouncementsEntity(
            "تفاوت فول اچ دی و اولترا اچ دی چیست؟",
            ":تاریخ انتشار 1399/9/6",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            true
        ),
        AnnouncementsEntity(
            "تکنولوژی اچ در آر چیست؟",
            ":تاریخ انتشار 1320/3/9",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            false
        ),
        AnnouncementsEntity(
            "چرا اسمارت؟",
            ":تاریخ انتشار 1990/3/9",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            true
        ),
        AnnouncementsEntity(
            "چگونه گوشی را به تلویزیون وصل کنیم",
            ":تاریخ انتشار 1399/3/9",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            true
        ),
        AnnouncementsEntity(
            "کیولد چگونه کار میکند؟",
            ":تاریخ انتشار 1390/2/9",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            false
        ),
        AnnouncementsEntity(
            "اندروید تیوی چیست؟",
            ":تاریخ انتشار 1390/3/12",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            true
        )
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAnnouncementsBinding.bind(view)
        fragmentAnnouncementsBinding = binding

        binding.announcemntsRecyclerview.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = AnnouncementsAdabter(mNicolasCageMovies)
        }



    }

    override fun onDestroyView() {
        fragmentAnnouncementsBinding = null
        super.onDestroyView()
    }
}