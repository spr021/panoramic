package com.example.panoramic.app.ui.movies.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.panoramic.R
import com.example.panoramic.app.ui.movies.OnMoviesItemClickListener
import com.example.panoramic.data.entity.MoviesEntity
import com.example.panoramic.databinding.FragmentMoviesBinding

class MoviesFragment : Fragment(R.layout.fragment_movies),
    OnMoviesItemClickListener {

    private val mNicolasCageMovies = listOf(
        MoviesEntity(
            "تفاوت فول اچ دی و اولترا اچ دی چیست؟",
            ":تاریخ انتشار 1399/9/6",
            "https://edularism.com/wp-content/uploads/2019/09/Image-1.png",
            true,
            "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ، و با استفاده از طراحان گرافیک است، چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است، و برای شرایط فعلی تکنولوژی مورد نیاز، و کاربردهای متنوع با هدف بهبود ابزارهای کاربردی می باشد، کتابهای زیادی در شصت و سه درصد گذشته حال و آینده، شناخت فراوان جامعه و متخصصان را می طلبد، تا با نرم افزارها شناخت بیشتری را برای طراحان رایانه ای علی الخصوص طراحان خلاقی، و فرهنگ پیشرو در زبان فارسی ایجاد کرد، در این صورت می توان امید داشت که تمام و دشواری موجود در ارائه راهکارها، و شرایط سخت تایپ به پایان رسد و زمان مورد نیاز شامل حروفچینی دستاوردهای اصلی، و جوابگوی سوالات پیوسته اهل دنیای موجود طراحی اساسا مورد استفاده قرار گیرد.",
            "https://hw17.cdn.asset.aparat.com/aparat-video/9c0ec665fdcf1261d3c21cd907f8fc1423519175-360p.mp4"
        ),
        MoviesEntity(
            "تفاوت فول اچ دی و اولترا اچ دی چیست؟",
            ":تاریخ انتشار 1399/9/6",
            "https://edularism.com/wp-content/uploads/2019/09/Image-1.png",
            true,
            "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ، و با استفاده از طراحان گرافیک است، چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است، و برای شرایط فعلی تکنولوژی مورد نیاز، و کاربردهای متنوع با هدف بهبود ابزارهای کاربردی می باشد، کتابهای زیادی در شصت و سه درصد گذشته حال و آینده، شناخت فراوان جامعه و متخصصان را می طلبد، تا با نرم افزارها شناخت بیشتری را برای طراحان رایانه ای علی الخصوص طراحان خلاقی، و فرهنگ پیشرو در زبان فارسی ایجاد کرد، در این صورت می توان امید داشت که تمام و دشواری موجود در ارائه راهکارها، و شرایط سخت تایپ به پایان رسد و زمان مورد نیاز شامل حروفچینی دستاوردهای اصلی، و جوابگوی سوالات پیوسته اهل دنیای موجود طراحی اساسا مورد استفاده قرار گیرد.",
            "https://hw17.cdn.asset.aparat.com/aparat-video/9c0ec665fdcf1261d3c21cd907f8fc1423519175-360p.mp4"
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
            adapter = MoviesAdabter(
                mNicolasCageMovies,
                this@MoviesFragment
            )
        }

        viewModel.viewState.observe(viewLifecycleOwner, viewStateObserver)
        viewModel.request()

    }

    override fun onItemClick(moviesEntity: MoviesEntity, position: Int) {
        findNavController().navigate(
            MoviesFragmentDirections.actionMoviesFragmentToMovieItemFragment(
                moviesEntity.title,
                moviesEntity.image,
                moviesEntity.text,
                moviesEntity.video
            )
        )
    }

    override fun onDestroyView() {
        fragmentMoviesBinding = null
        super.onDestroyView()
    }
}