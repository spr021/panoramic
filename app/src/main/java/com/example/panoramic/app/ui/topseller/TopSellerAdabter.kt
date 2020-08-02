package com.example.panoramic.app.ui.topseller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.panoramic.data.entity.TopSellerEntity
import com.example.panoramic.remote.model.Rank

class TopSellerAdabter(private val list: List<Rank>) : RecyclerView.Adapter<TopSellerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopSellerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TopSellerViewHolder(
            inflater,
            parent
        )
    }

    override fun onBindViewHolder(holder: TopSellerViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}