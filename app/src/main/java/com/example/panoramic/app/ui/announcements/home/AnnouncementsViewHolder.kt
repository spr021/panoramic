package com.example.panoramic.app.ui.announcements.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.panoramic.R
import com.example.panoramic.app.ui.announcements.OnAnnouncementsItemClickListener
import com.example.panoramic.app.ui.movies.OnMoviesItemClickListener
import com.example.panoramic.data.entity.AnnouncementsEntity
import com.squareup.picasso.Picasso

class AnnouncementsViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_announcements, parent, false)) {
    private var mTitleView: TextView? = null
    private var mDateView: TextView? = null
    private var mImageView: ImageView? = null
    private var mSeenView: View? = null


    init {
        mTitleView = itemView.findViewById(R.id.list_title)
        mDateView = itemView.findViewById(R.id.list_description)
        mImageView = itemView.findViewById(R.id.list_image)
        mSeenView = itemView.findViewById(R.id.notification)
    }

    fun bind(announcement: AnnouncementsEntity, action: OnAnnouncementsItemClickListener) {
        mTitleView?.text = announcement.title
        mDateView?.text = announcement.date
        Picasso.get()
            .load(announcement.image)
            .placeholder(R.drawable.placeholder_image)
            .into(mImageView)
        mSeenView?.visibility = if (announcement.seen) {
            View.VISIBLE
        } else {
            View.GONE
        }

        itemView.setOnClickListener {
            action.onItemClick(announcement, adapterPosition)
        }
    }

}