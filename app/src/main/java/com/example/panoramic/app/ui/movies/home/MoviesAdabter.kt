package com.example.panoramic.app.ui.movies.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.panoramic.app.ui.movies.OnMoviesItemClickListener
import com.example.panoramic.data.entity.MoviesEntity

class MoviesAdabter(private val list: List<MoviesEntity>, var clickListener: OnMoviesItemClickListener) : RecyclerView.Adapter<MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MoviesViewHolder(
            inflater,
            parent
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(list[position], clickListener)
    }

    override fun getItemCount(): Int = list.size

}