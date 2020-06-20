package com.example.panoramic.app.ui.movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
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
        ),
        MoviesEntity(
            "تکنولوژی اچ در آر چیست؟",
            ":تاریخ انتشار 1320/3/9",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            false
        ),
        MoviesEntity(
            "چرا اسمارت؟",
            ":تاریخ انتشار 1990/3/9",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            true
        ),
        MoviesEntity(
            "چگونه گوشی را به تلویزیون وصل کنیم",
            ":تاریخ انتشار 1399/3/9",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            true
        ),
        MoviesEntity(
            "کیولد چگونه کار میکند؟",
            ":تاریخ انتشار 1390/2/9",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            false
        ),
        MoviesEntity(
            "اندروید تیوی چیست؟",
            ":تاریخ انتشار 1390/3/12",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            true
        ),
        MoviesEntity(
            "تفاوت فول اچ دی و اولترا اچ دی چیست؟",
            ":تاریخ انتشار 1399/9/6",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            false
        ),
        MoviesEntity(
            "تکنولوژی اچ در آر چیست؟",
            ":تاریخ انتشار 1320/3/9",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            false
        ),
        MoviesEntity(
            "چرا اسمارت؟",
            ":تاریخ انتشار 1990/3/9",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            true
        ),
        MoviesEntity(
            "چگونه گوشی را به تلویزیون وصل کنیم",
            ":تاریخ انتشار 1399/3/9",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            true
        ),
        MoviesEntity(
            "کیولد چگونه کار میکند؟",
            ":تاریخ انتشار 1390/2/9",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            false
        ),
        MoviesEntity(
            "اندروید تیوی چیست؟",
            ":تاریخ انتشار 1390/3/12",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            false
        ),
        MoviesEntity(
            "تفاوت فول اچ دی و اولترا اچ دی چیست؟",
            ":تاریخ انتشار 1399/9/6",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            true
        ),
        MoviesEntity(
            "تکنولوژی اچ در آر چیست؟",
            ":تاریخ انتشار 1320/3/9",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            false
        ),
        MoviesEntity(
            "چرا اسمارت؟",
            ":تاریخ انتشار 1990/3/9",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            true
        ),
        MoviesEntity(
            "چگونه گوشی را به تلویزیون وصل کنیم",
            ":تاریخ انتشار 1399/3/9",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            true
        ),
        MoviesEntity(
            "کیولد چگونه کار میکند؟",
            ":تاریخ انتشار 1390/2/9",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            false
        ),
        MoviesEntity(
            "اندروید تیوی چیست؟",
            ":تاریخ انتشار 1390/3/12",
            "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
            true
        )
    )

    private var fragmentMoviesBinding: FragmentMoviesBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMoviesBinding.bind(view)
        fragmentMoviesBinding = binding

        binding.moviesRecyclerview.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = MoviesAdabter(mNicolasCageMovies)
        }

    }

    override fun onDestroyView() {
        fragmentMoviesBinding = null
        super.onDestroyView()
    }
}