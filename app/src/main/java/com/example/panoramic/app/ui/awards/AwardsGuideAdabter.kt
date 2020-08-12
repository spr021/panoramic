package com.example.panoramic.app.ui.awards

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.panoramic.remote.model.Items

class AwardsGuideAdabter(private val list: List<Items>, private var clickListener: OnAwardItemClickListener) : RecyclerView.Adapter<AwardsGuideViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AwardsGuideViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AwardsGuideViewHolder(
            inflater,
            parent
        )
    }

    override fun onBindViewHolder(holder: AwardsGuideViewHolder, position: Int) {
        holder.bind(list[position], clickListener)
    }

    override fun getItemCount(): Int = list.size

}