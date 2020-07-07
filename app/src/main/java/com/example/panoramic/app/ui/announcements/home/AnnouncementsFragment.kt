package com.example.panoramic.app.ui.announcements.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.panoramic.R
import com.example.panoramic.app.ui.announcements.OnAnnouncementsItemClickListener
import com.example.panoramic.data.entity.AnnouncementsEntity
import com.example.panoramic.databinding.FragmentAnnouncementsBinding

class AnnouncementsFragment : Fragment(R.layout.fragment_announcements),
    OnAnnouncementsItemClickListener {

    private var fragmentAnnouncementsBinding: FragmentAnnouncementsBinding? = null
    private val viewModel: AnnouncementsViewModel by viewModels()
    lateinit var recyclerViewInformation: AnnouncementsEntity

    private val mNicolasCageMovies = listOf(
        AnnouncementsEntity(
            "تفاوت فول اچ دی و اولترا اچ دی چیست؟",
            "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ، و با استفاده از طراحان گرافیک است، چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است، و برای شرایط فعلی تکنولوژی مورد نیاز، و کاربردهای متنوع با هدف بهبود ابزارهای کاربردی می باشد، کتابهای زیادی در شصت و سه درصد گذشته حال و آینده، شناخت فراوان جامعه و متخصصان را می طلبد، تا با نرم افزارها شناخت بیشتری را برای طراحان رایانه ای علی الخصوص طراحان خلاقی، و فرهنگ پیشرو در زبان فارسی ایجاد کرد، در این صورت می توان امید داشت که تمام و دشواری موجود در ارائه راهکارها، و شرایط سخت تایپ به پایان رسد و زمان مورد نیاز شامل حروفچینی دستاوردهای اصلی، و جوابگوی سوالات پیوسته اهل دنیای موجود طراحی اساسا مورد استفاده قرار گیرد.",
            ":تاریخ انتشار 1399/9/6",
            "https://edularism.com/wp-content/uploads/2019/09/Image-1.png",
            true
        ),AnnouncementsEntity(
            "تفاوت فول اچ دی و اولترا اچ دی چیست؟",
            "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ، و با استفاده از طراحان گرافیک است، چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است، و برای شرایط فعلی تکنولوژی مورد نیاز، و کاربردهای متنوع با هدف بهبود ابزارهای کاربردی می باشد، کتابهای زیادی در شصت و سه درصد گذشته حال و آینده، شناخت فراوان جامعه و متخصصان را می طلبد، تا با نرم افزارها شناخت بیشتری را برای طراحان رایانه ای علی الخصوص طراحان خلاقی، و فرهنگ پیشرو در زبان فارسی ایجاد کرد، در این صورت می توان امید داشت که تمام و دشواری موجود در ارائه راهکارها، و شرایط سخت تایپ به پایان رسد و زمان مورد نیاز شامل حروفچینی دستاوردهای اصلی، و جوابگوی سوالات پیوسته اهل دنیای موجود طراحی اساسا مورد استفاده قرار گیرد.",
            ":تاریخ انتشار 1399/9/6",
            "https://edularism.com/wp-content/uploads/2019/09/Image-1.png",
            false
        ),AnnouncementsEntity(
            "تفاوت فول اچ دی و اولترا اچ دی چیست؟",
            "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ، و با استفاده از طراحان گرافیک است، چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است، و برای شرایط فعلی تکنولوژی مورد نیاز، و کاربردهای متنوع با هدف بهبود ابزارهای کاربردی می باشد، کتابهای زیادی در شصت و سه درصد گذشته حال و آینده، شناخت فراوان جامعه و متخصصان را می طلبد، تا با نرم افزارها شناخت بیشتری را برای طراحان رایانه ای علی الخصوص طراحان خلاقی، و فرهنگ پیشرو در زبان فارسی ایجاد کرد، در این صورت می توان امید داشت که تمام و دشواری موجود در ارائه راهکارها، و شرایط سخت تایپ به پایان رسد و زمان مورد نیاز شامل حروفچینی دستاوردهای اصلی، و جوابگوی سوالات پیوسته اهل دنیای موجود طراحی اساسا مورد استفاده قرار گیرد.",
            ":تاریخ انتشار 1399/9/6",
            "https://edularism.com/wp-content/uploads/2019/09/Image-1.png",
            false
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
            adapter =
                AnnouncementsAdabter(
                    mNicolasCageMovies,
                    this@AnnouncementsFragment
                )
        }

        viewModel.viewState.observe(viewLifecycleOwner, viewStateObserver)
        viewModel.request()

    }

    override fun onDestroyView() {
        fragmentAnnouncementsBinding = null
        super.onDestroyView()
    }

    override fun onItemClick(announcementsEntity: AnnouncementsEntity, position: Int) {
        findNavController().navigate(
            AnnouncementsFragmentDirections.actionAnnouncementsFragmentToAnnouncementItemFragment(
                announcementsEntity.title,
                announcementsEntity.text,
                announcementsEntity.image
            )
        )
    }
}