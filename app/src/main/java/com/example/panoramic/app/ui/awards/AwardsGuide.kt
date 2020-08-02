package com.example.panoramic.app.ui.awards

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.panoramic.R
import com.example.panoramic.app.SlideView
import com.example.panoramic.data.entity.AwardGuideEntity

class AwardsGuide : Fragment(R.layout.fragment_awards_guide), OnAwardItemClickListener {

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
            34,
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
            35,
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
            36,
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
            37,
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
            38,
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
            39,
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
            40,
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
            41,
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
            42,
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
            43,
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
            44,
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
            45,
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
            46,
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
            47,
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
            48,
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
            49,
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
            50,
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
            51,
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
            52,
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
            53,
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
            54,
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
            55,
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
            56,
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
            57,
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
            58,
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
            59,
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
            60,
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
            61,
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
            62,
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
            63,
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
            64,
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

            val orientation = resources.configuration.orientation
            layoutManager = if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            } else {
                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            }
            adapter = AwardsGuideAdabter(mNicolasCageMovies, this@AwardsGuide)

        }
    }

    @Suppress("NAME_SHADOWING")
    override fun onItemClick(awardGuideEntity: AwardGuideEntity, position: Int, view: View) {
        val arrow = view.findViewById<ImageView>(R.id.arrows)
        val viewList = listOf(
            view.findViewById<TextView>(R.id.size),
            view.findViewById<TextView>(R.id.size_value),
            view.findViewById<TextView>(R.id.type),
            view.findViewById<TextView>(R.id.type_value),
            view.findViewById<TextView>(R.id.panel_type),
            view.findViewById<TextView>(R.id.panel_type_value),
            view.findViewById<TextView>(R.id.HTMI),
            view.findViewById<TextView>(R.id.HTMI_value),
            view.findViewById<TextView>(R.id.USB),
            view.findViewById<TextView>(R.id.USB_value),
            view.findViewById<TextView>(R.id.OS),
            view.findViewById<TextView>(R.id.OS_value),
            view.findViewById<View>(R.id.line_breaker),
            view.findViewById<TextView>(R.id.price),
            view.findViewById<TextView>(R.id.price_value)
        )
        if (arrow.visibility == View.VISIBLE) {
            arrow.visibility = View.GONE

            viewList.map { view ->
                view.visibility = View.VISIBLE
            }
            SlideView(
                view,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        } else {
            arrow.visibility = View.VISIBLE

            viewList.map { view ->
                view.visibility = View.GONE
            }
            SlideView(
                view,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }
    }
}