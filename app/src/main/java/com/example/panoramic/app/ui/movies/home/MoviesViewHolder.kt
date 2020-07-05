package com.example.panoramic.app.ui.movies.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.panoramic.R
import com.example.panoramic.app.ui.movies.OnMoviesItemClickListener
import com.example.panoramic.data.entity.MoviesEntity
import com.squareup.picasso.Picasso

class MoviesViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_movies, parent, false)) {
    private var mTitleView: TextView? = null
    private var mTimeView: TextView? = null
    private var mImageView: ImageView? = null
    private var mSeenView: CheckBox? = null


    init {
        mTitleView = itemView.findViewById(R.id.list_title)
        mTimeView = itemView.findViewById(R.id.list_time)
        mImageView = itemView.findViewById(R.id.list_image)
        mSeenView = itemView.findViewById(R.id.list_checkBox)
    }

    fun bind(movie: MoviesEntity, action: OnMoviesItemClickListener) {
        mTitleView?.text = movie.title
        mTimeView?.text = movie.time
        Picasso.get()
            .load(movie.image)
            .placeholder(R.drawable.placeholder_image)
            .into(mImageView)
        mSeenView?.isChecked = movie.seen

        itemView.setOnClickListener {
            action.onItemClick(movie, adapterPosition)
        }
    }
}