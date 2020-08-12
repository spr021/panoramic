package com.example.panoramic.app.ui.topseller

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.panoramic.R
import com.example.panoramic.data.entity.TopSellerEntity
import com.example.panoramic.remote.model.Rank
import com.squareup.picasso.Picasso

class TopSellerViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_topseller, parent, false)) {
    private var mRankView: TextView? = null
    private var mImageView: ImageView? = null
    private var mNameView: TextView? = null
    private var mScoreView: TextView? = null
    private var mShopNameView: TextView? = null
    private var mStateView: TextView? = null


    init {
        mRankView = itemView.findViewById(R.id.rank_number)
        mImageView = itemView.findViewById(R.id.profile_pictuer)
        mNameView = itemView.findViewById(R.id.name)
        mScoreView = itemView.findViewById(R.id.total_score_value)
        mShopNameView = itemView.findViewById(R.id.shop_name)
        mStateView = itemView.findViewById(R.id.state)
    }

    fun bind(person: Rank) {
        mRankView?.text = person.rank.toString()
        Picasso.get()
            .load(person.prof_pic)
            .into(mImageView)
        mNameView?.text = person.name
        mScoreView?.text = person.score
        mShopNameView?.text = person.shop_name
        //mStateView?.text = person.state
    }

}