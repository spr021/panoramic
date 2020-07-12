package com.example.panoramic.app.ui.awards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.panoramic.R
import com.example.panoramic.app.ui.topseller.TopSellerAdabter
import com.example.panoramic.data.entity.AwardGuideEntity
import com.example.panoramic.data.entity.TopSellerEntity

class AwardsGuide : Fragment(R.layout.fragment_awards_guide) {

    private val mNicolasCageMovies = listOf(
        AwardGuideEntity(
            "PA32BA1633",
            33,
            "https://dyw7ncnq1en5l.cloudfront.net/optim/product/57/57223/72f2e92d-mi-tv-4s-65__450_400.jpeg",
            90,
            "بیسیک",
            "QLED",
            3,
            4,
            "Android 7.1",
            "32,234,000"
        ),
        AwardGuideEntity(
            "PA32BA1633",
            33,
            "https://dyw7ncnq1en5l.cloudfront.net/optim/product/57/57223/72f2e92d-mi-tv-4s-65__450_400.jpeg",
            90,
            "بیسیک",
            "QLED",
            3,
            4,
            "Android 7.1",
            "32,234,000"
        ),
        AwardGuideEntity(
            "PA32BA1633",
            33,
            "https://dyw7ncnq1en5l.cloudfront.net/optim/product/57/57223/72f2e92d-mi-tv-4s-65__450_400.jpeg",
            90,
            "بیسیک",
            "QLED",
            3,
            4,
            "Android 7.1",
            "32,234,000"
        ),
        AwardGuideEntity(
            "PA32BA1633",
            33,
            "https://dyw7ncnq1en5l.cloudfront.net/optim/product/57/57223/72f2e92d-mi-tv-4s-65__450_400.jpeg",
            90,
            "بیسیک",
            "QLED",
            3,
            4,
            "Android 7.1",
            "32,234,000"
        ),
        AwardGuideEntity(
            "PA32BA1633",
            33,
            "https://dyw7ncnq1en5l.cloudfront.net/optim/product/57/57223/72f2e92d-mi-tv-4s-65__450_400.jpeg",
            90,
            "بیسیک",
            "QLED",
            3,
            4,
            "Android 7.1",
            "32,234,000"
        ),
        AwardGuideEntity(
            "PA32BA1633",
            33,
            "https://dyw7ncnq1en5l.cloudfront.net/optim/product/57/57223/72f2e92d-mi-tv-4s-65__450_400.jpeg",
            90,
            "بیسیک",
            "QLED",
            3,
            4,
            "Android 7.1",
            "32,234,000"
        ),
        AwardGuideEntity(
            "PA32BA1633",
            33,
            "https://dyw7ncnq1en5l.cloudfront.net/optim/product/57/57223/72f2e92d-mi-tv-4s-65__450_400.jpeg",
            90,
            "بیسیک",
            "QLED",
            3,
            4,
            "Android 7.1",
            "32,234,000"
        ),
        AwardGuideEntity(
            "PA32BA1633",
            33,
            "https://dyw7ncnq1en5l.cloudfront.net/optim/product/57/57223/72f2e92d-mi-tv-4s-65__450_400.jpeg",
            90,
            "بیسیک",
            "QLED",
            3,
            4,
            "Android 7.1",
            "32,234,000"
        ),
        AwardGuideEntity(
            "PA32BA1633",
            33,
            "https://dyw7ncnq1en5l.cloudfront.net/optim/product/57/57223/72f2e92d-mi-tv-4s-65__450_400.jpeg",
            90,
            "بیسیک",
            "QLED",
            3,
            4,
            "Android 7.1",
            "32,234,000"
        ),
        AwardGuideEntity(
            "PA32BA1633",
            33,
            "https://dyw7ncnq1en5l.cloudfront.net/optim/product/57/57223/72f2e92d-mi-tv-4s-65__450_400.jpeg",
            90,
            "بیسیک",
            "QLED",
            3,
            4,
            "Android 7.1",
            "32,234,000"
        ),
        AwardGuideEntity(
            "PA32BA1633",
            33,
            "https://dyw7ncnq1en5l.cloudfront.net/optim/product/57/57223/72f2e92d-mi-tv-4s-65__450_400.jpeg",
            90,
            "بیسیک",
            "QLED",
            3,
            4,
            "Android 7.1",
            "32,234,000"
        ),
        AwardGuideEntity(
            "PA32BA1633",
            33,
            "https://dyw7ncnq1en5l.cloudfront.net/optim/product/57/57223/72f2e92d-mi-tv-4s-65__450_400.jpeg",
            90,
            "بیسیک",
            "QLED",
            3,
            4,
            "Android 7.1",
            "32,234,000"
        ),
        AwardGuideEntity(
            "PA32BA1633",
            33,
            "https://dyw7ncnq1en5l.cloudfront.net/optim/product/57/57223/72f2e92d-mi-tv-4s-65__450_400.jpeg",
            90,
            "بیسیک",
            "QLED",
            3,
            4,
            "Android 7.1",
            "32,234,000"
        ),
        AwardGuideEntity(
            "PA32BA1633",
            33,
            "https://dyw7ncnq1en5l.cloudfront.net/optim/product/57/57223/72f2e92d-mi-tv-4s-65__450_400.jpeg",
            90,
            "بیسیک",
            "QLED",
            3,
            4,
            "Android 7.1",
            "32,234,000"
        ),
        AwardGuideEntity(
            "PA32BA1633",
            33,
            "https://dyw7ncnq1en5l.cloudfront.net/optim/product/57/57223/72f2e92d-mi-tv-4s-65__450_400.jpeg",
            90,
            "بیسیک",
            "QLED",
            3,
            4,
            "Android 7.1",
            "32,234,000"
        )
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.award_recyclerview).apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = AwardsGuideAdabter(mNicolasCageMovies)
        }

    }
}