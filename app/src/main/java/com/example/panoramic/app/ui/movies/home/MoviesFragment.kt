package com.example.panoramic.app.ui.movies.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.panoramic.R
import com.example.panoramic.app.ui.announcements.home.AnnouncementsAdabter
import com.example.panoramic.app.ui.movies.OnMoviesItemClickListener
import com.example.panoramic.data.entity.MoviesEntity
import com.example.panoramic.databinding.FragmentMoviesBinding
import com.example.panoramic.remote.model.Movie
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_movie_item.*

class MoviesFragment : Fragment(R.layout.fragment_movies), OnMoviesItemClickListener {

    private val mNicolasCageMovies = listOf(
        MoviesEntity(
            "تفاوت فول اچ دی و اولترا اچ دی چیست؟",
            ":تاریخ انتشار 1399/9/6",
            "https://edularism.com/wp-content/uploads/2019/09/Image-1.png",
            true,
            "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ، و با استفاده از طراحان گرافیک است، چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است، و برای شرایط فعلی تکنولوژی مورد نیاز، و کاربردهای متنوع با هدف بهبود ابزارهای کاربردی می باشد، کتابهای زیادی در شصت و سه درصد گذشته حال و آینده، شناخت فراوان جامعه و متخصصان را می طلبد، تا با نرم افزارها شناخت بیشتری را برای طراحان رایانه ای علی الخصوص طراحان خلاقی، و فرهنگ پیشرو در زبان فارسی ایجاد کرد، در این صورت می توان امید داشت که تمام و دشواری موجود در ارائه راهکارها، و شرایط سخت تایپ به پایان رسد و زمان مورد نیاز شامل حروفچینی دستاوردهای اصلی، و جوابگوی سوالات پیوسته اهل دنیای موجود طراحی اساسا مورد استفاده قرار گیرد.",
            "https://as3.cdn.asset.aparat.com/aparat-video/5ea0f40c8e8ffdcd72f5ceb2fb23d81824246597-720p.mp4"
        ),
        MoviesEntity(
            "تفاوت فول اچ دی و اولترا اچ دی چیست؟",
            ":تاریخ انتشار 1399/9/6",
            "https://edularism.com/wp-content/uploads/2019/09/Image-1.png",
            true,
            "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ، و با استفاده از طراحان گرافیک است، چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است، و برای شرایط فعلی تکنولوژی مورد نیاز، و کاربردهای متنوع با هدف بهبود ابزارهای کاربردی می باشد، کتابهای زیادی در شصت و سه درصد گذشته حال و آینده، شناخت فراوان جامعه و متخصصان را می طلبد، تا با نرم افزارها شناخت بیشتری را برای طراحان رایانه ای علی الخصوص طراحان خلاقی، و فرهنگ پیشرو در زبان فارسی ایجاد کرد، در این صورت می توان امید داشت که تمام و دشواری موجود در ارائه راهکارها، و شرایط سخت تایپ به پایان رسد و زمان مورد نیاز شامل حروفچینی دستاوردهای اصلی، و جوابگوی سوالات پیوسته اهل دنیای موجود طراحی اساسا مورد استفاده قرار گیرد.",
            "https://as3.cdn.asset.aparat.com/aparat-video/5ea0f40c8e8ffdcd72f5ceb2fb23d81824246597-720p.mp4"
        )
    )

    private var fragmentMoviesBinding: FragmentMoviesBinding? = null
    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var recyclerViewInformation: MoviesEntity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMoviesBinding.bind(view)
        fragmentMoviesBinding = binding

        //get movie list
        val cookie =
            activity?.getSharedPreferences("COOKIE", Context.MODE_PRIVATE)!!.getString("COOKIE", "")
        viewModel.getMovieList(cookie)

        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            if (it.success) {
                view.findViewById<ConstraintLayout>(R.id.loading).visibility = View.GONE
                binding.moviesRecyclerview.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = MoviesAdabter(it.items, this@MoviesFragment)
                }
            }
        })

    }

    override fun onItemClick(movie: Movie, position: Int) {
        //val extras = FragmentNavigatorExtras(image_cover to "image_cover")
        findNavController().navigate(
            MoviesFragmentDirections.actionMoviesFragmentToMovieItemFragment(
                movie.title,
                movie.film_pic,
                movie.text,
                movie.link,
                movie.id
            )
        )
    }

    override fun onDestroyView() {
        fragmentMoviesBinding = null
        super.onDestroyView()
    }
}