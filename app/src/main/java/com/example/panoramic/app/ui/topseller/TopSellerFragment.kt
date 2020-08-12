package com.example.panoramic.app.ui.topseller

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.panoramic.R

class TopSellerFragment : Fragment(R.layout.fragment_top_seller) {

    private lateinit var viewModel: TopSellerViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(TopSellerViewModel::class.java)

        val cookie =
            activity?.getSharedPreferences("COOKIE", Context.MODE_PRIVATE)!!.getString("COOKIE", "")
        viewModel.getRankList(cookie)
        viewModel.getList.observe(viewLifecycleOwner, Observer {

            if (it.success) {
                view.findViewById<ConstraintLayout>(R.id.loading).visibility = View.GONE
                view.findViewById<RecyclerView>(R.id.top_seller_recyclerview).apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = TopSellerAdabter(it.rank)
                }
            }
        })
    }
}