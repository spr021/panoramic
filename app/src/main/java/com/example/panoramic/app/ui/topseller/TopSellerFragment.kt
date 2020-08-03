package com.example.panoramic.app.ui.topseller

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.panoramic.R
import com.example.panoramic.app.ui.home.HomeViewModel
import com.example.panoramic.data.entity.TopSellerEntity

class TopSellerFragment : Fragment(R.layout.fragment_top_seller) {

    private lateinit var viewModel: TopSellerViewModel

    private val mNicolasCageMovies = listOf(
        TopSellerEntity(
            "صابر پوررحیمی",
            790,
            "فروشگاه سوگند",
            "تهران",
            "https://www.pikpng.com/pngl/b/80-805068_my-profile-icon-blank-profile-picture-circle-clipart.png",
            1
        ),
        TopSellerEntity(
            "صابر پوررحیمی",
            790,
            "فروشگاه سوگند",
            "تهران",
            "https://www.pikpng.com/pngl/b/80-805068_my-profile-icon-blank-profile-picture-circle-clipart.png",
            2
        )
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(TopSellerViewModel::class.java)

        val cookie =
            activity?.getSharedPreferences("COOKIE", Context.MODE_PRIVATE)!!.getString("COOKIE", "")
        viewModel.getRankList(cookie)
        viewModel.getList.observe(viewLifecycleOwner, Observer {

            if (it.success) {
                view.findViewById<RecyclerView>(R.id.top_seller_recyclerview).apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = TopSellerAdabter(it.rank)
                }
            }
        })
    }
}