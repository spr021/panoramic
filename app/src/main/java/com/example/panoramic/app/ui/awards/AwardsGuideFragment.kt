package com.example.panoramic.app.ui.awards

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.panoramic.R
import com.example.panoramic.app.SlideView
import com.example.panoramic.databinding.FragmentAwardsGuideBinding
import com.example.panoramic.databinding.FragmentHomeBinding
import com.example.panoramic.remote.model.Items

class AwardsGuideFragment : Fragment(R.layout.fragment_awards_guide), OnAwardItemClickListener {

    private lateinit var viewModel: AwardsGuideViewModel
    private var awardsGuideBinding: FragmentAwardsGuideBinding? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAwardsGuideBinding.bind(view)
        awardsGuideBinding = binding
        viewModel = ViewModelProviders.of(this).get(AwardsGuideViewModel::class.java)
        

        val cookie =
            activity?.getSharedPreferences("COOKIE", Context.MODE_PRIVATE)!!.getString("COOKIE", "")
        viewModel.getAnnouncementsList(cookie)

        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            if (it.success) {
                binding.loading.visibility = View.GONE
                view.findViewById<RecyclerView>(R.id.award_recyclerview).apply {

                    val orientation = resources.configuration.orientation
                    layoutManager = if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    } else {
                        StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
                    }
                    adapter = AwardsGuideAdabter(it.items, this@AwardsGuideFragment)

                }
            }
        })

        view.findViewById<ConstraintLayout>(R.id.constraintLayout).setOnClickListener {
            val extras =
                FragmentNavigatorExtras(binding.constraintLayout to binding.constraintLayout.transitionName)
            findNavController().navigate(R.id.action_awards_guide_to_rulesFragment, null, null, extras)
        }
    }

    @Suppress("NAME_SHADOWING")
    override fun onItemClick(awardGuideEntity: Items, position: Int, view: View) {
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

    override fun onDestroyView() {
        awardsGuideBinding = null
        super.onDestroyView()
    }
}