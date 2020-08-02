package com.example.panoramic.app.ui.score

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.panoramic.remote.model.Product

class ScoreAdabter(private val list: List<Product>) : RecyclerView.Adapter<ScoreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ScoreViewHolder(
            inflater,
            parent
        )
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}