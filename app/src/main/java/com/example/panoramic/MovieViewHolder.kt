package com.example.panoramic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_announcements, parent, false)) {
    private var mTitleView: TextView? = null
    private var mDateView: TextView? = null
    private var mImageView: ImageView? = null
    private var mSeenView: View? = null
    private var mBoxView: View? = null


    init {
        mTitleView = itemView.findViewById(R.id.list_title)
        mDateView = itemView.findViewById(R.id.list_description)
        mImageView = itemView.findViewById(R.id.list_image)
        mSeenView = itemView.findViewById(R.id.notification)
        mBoxView = itemView.findViewById(R.id.list_box)
    }

    fun bind(movie: Movie) {
        mTitleView?.text = movie.title
        mDateView?.text = movie.date
        Picasso.get()
            .load(movie.image)
            .placeholder(R.drawable.placeholder_image)
            .into(mImageView)
        mSeenView?.visibility = if (movie.seen) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

}