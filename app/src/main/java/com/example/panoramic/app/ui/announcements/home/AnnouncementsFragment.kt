package com.example.panoramic.app.ui.announcements.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.panoramic.R
import com.example.panoramic.app.ui.announcements.OnAnnouncementsItemClickListener
import com.example.panoramic.app.ui.topseller.TopSellerAdabter
import com.example.panoramic.data.entity.AnnouncementsEntity
import com.example.panoramic.databinding.FragmentAnnouncementsBinding
import com.example.panoramic.remote.model.Notif

class AnnouncementsFragment : Fragment(R.layout.fragment_announcements),
    OnAnnouncementsItemClickListener {

    private var fragmentAnnouncementsBinding: FragmentAnnouncementsBinding? = null
    private val viewModel: AnnouncementsViewModel by viewModels()

    private val mNicolasCageMovies = listOf(
        AnnouncementsEntity(
            "تفاوت فول اچ دی و اولترا اچ دی چیست؟",
            "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ، و با استفاده از طراحان گرافیک است، چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است، و برای شرایط فعلی تکنولوژی مورد نیاز، و کاربردهای متنوع با هدف بهبود ابزارهای کاربردی می باشد، کتابهای زیادی در شصت و سه درصد گذشته حال و آینده، شناخت فراوان جامعه و متخصصان را می طلبد، تا با نرم افزارها شناخت بیشتری را برای طراحان رایانه ای علی الخصوص طراحان خلاقی، و فرهنگ پیشرو در زبان فارسی ایجاد کرد، در این صورت می توان امید داشت که تمام و دشواری موجود در ارائه راهکارها، و شرایط سخت تایپ به پایان رسد و زمان مورد نیاز شامل حروفچینی دستاوردهای اصلی، و جوابگوی سوالات پیوسته اهل دنیای موجود طراحی اساسا مورد استفاده قرار گیرد.",
            ":تاریخ انتشار 1399/9/6",
            "https://edularism.com/wp-content/uploads/2019/09/Image-1.png",
            true
        ), AnnouncementsEntity(
            "تفاوت فول اچ دی و اولترا اچ دی چیست؟",
            "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ، و با استفاده از طراحان گرافیک است، چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است، و برای شرایط فعلی تکنولوژی مورد نیاز، و کاربردهای متنوع با هدف بهبود ابزارهای کاربردی می باشد، کتابهای زیادی در شصت و سه درصد گذشته حال و آینده، شناخت فراوان جامعه و متخصصان را می طلبد، تا با نرم افزارها شناخت بیشتری را برای طراحان رایانه ای علی الخصوص طراحان خلاقی، و فرهنگ پیشرو در زبان فارسی ایجاد کرد، در این صورت می توان امید داشت که تمام و دشواری موجود در ارائه راهکارها، و شرایط سخت تایپ به پایان رسد و زمان مورد نیاز شامل حروفچینی دستاوردهای اصلی، و جوابگوی سوالات پیوسته اهل دنیای موجود طراحی اساسا مورد استفاده قرار گیرد.",
            ":تاریخ انتشار 1399/9/6",
            "https://edularism.com/wp-content/uploads/2019/09/Image-1.png",
            false
        ), AnnouncementsEntity(
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

        //request get list
        val cookie =
            activity?.getSharedPreferences("COOKIE", Context.MODE_PRIVATE)!!.getString("COOKIE", "")
        viewModel.getAnnouncementsList(cookie)

        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            if (it.success) {
                view.findViewById<ConstraintLayout>(R.id.loading).visibility = View.GONE
                binding.announcemntsRecyclerview.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = AnnouncementsAdabter(it.items, this@AnnouncementsFragment)
                }
            }
        })

    }

    override fun onDestroyView() {
        fragmentAnnouncementsBinding = null
        super.onDestroyView()
    }

    override fun onItemClick(announcementsDto: Notif, position: Int) {
        findNavController().navigate(
            AnnouncementsFragmentDirections.actionAnnouncementsFragmentToAnnouncementItemFragment(
                announcementsDto.title,
                announcementsDto.text,
                announcementsDto.pic,
                announcementsDto.id
            )
        )
    }
}