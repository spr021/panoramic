package com.example.panoramic.app.ui.awards

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
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
    private var mArrow: ImageView? = null
    private var viewList: List<View>? = null


    init {
        mModelView = itemView.findViewById(R.id.model_number)
        mInchView = itemView.findViewById(R.id.inch_value)
        mImageView = itemView.findViewById(R.id.tv_image)
        mScoreView = itemView.findViewById(R.id.score)
        mArrow = itemView.findViewById(R.id.arrows)
        viewList = listOf(
            itemView.findViewById<TextView>(R.id.size),
            itemView.findViewById<TextView>(R.id.size_value),
            itemView.findViewById<TextView>(R.id.type),
            itemView.findViewById<TextView>(R.id.type_value),
            itemView.findViewById<TextView>(R.id.panel_type),
            itemView.findViewById<TextView>(R.id.panel_type_value),
            itemView.findViewById<TextView>(R.id.HTMI),
            itemView.findViewById<TextView>(R.id.HTMI_value),
            itemView.findViewById<TextView>(R.id.USB),
            itemView.findViewById<TextView>(R.id.USB_value),
            itemView.findViewById<TextView>(R.id.OS),
            itemView.findViewById<TextView>(R.id.OS_value),
            itemView.findViewById<View>(R.id.line_breaker),
            itemView.findViewById<TextView>(R.id.price),
            itemView.findViewById<TextView>(R.id.price_value)
        )
    }

    fun bind(product: AwardGuideEntity, action: OnAwardItemClickListener) {
        mModelView?.text = product.modelNumber
        mInchView?.text = product.size.toString()
        Picasso.get()
            .load(product.image)
            .placeholder(R.drawable.placeholder_image)
            .into(mImageView)
        mScoreView?.text = product.score.toString()

        mArrow!!.visibility = View.VISIBLE
        viewList!!.map { view ->
            view.visibility = View.GONE
        }
        itemView.layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
        itemView.setOnClickListener {
            action.onItemClick(product, adapterPosition, itemView)
        }
    }
}