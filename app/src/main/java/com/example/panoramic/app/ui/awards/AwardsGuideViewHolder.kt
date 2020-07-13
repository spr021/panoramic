package com.example.panoramic.app.ui.awards

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.panoramic.R
import com.example.panoramic.app.CustomToast
import com.example.panoramic.app.SlideView
import com.example.panoramic.data.entity.AwardGuideEntity
import com.squareup.picasso.Picasso

class AwardsGuideViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_awards_guide, parent, false)) {
    private var mModelView: TextView? = null
    private var mInchView: TextView? = null
    private var mImageView: ImageView? = null
    private var mScoreView: TextView? = null


    init {
        mModelView = itemView.findViewById(R.id.model_number)
        mInchView = itemView.findViewById(R.id.inch_value)
        mImageView = itemView.findViewById(R.id.tv_image)
        mScoreView = itemView.findViewById(R.id.score)
    }

    fun bind(product: AwardGuideEntity, action: OnAwardItemClickListener) {
        mModelView?.text = product.modelNumber
        mInchView?.text = product.size.toString()
        Picasso.get()
            .load(product.image)
            .into(mImageView)
        mScoreView?.text = product.score.toString()



        itemView.setOnClickListener {
            action.onItemClick(product, adapterPosition, itemView)
        }
    }
}