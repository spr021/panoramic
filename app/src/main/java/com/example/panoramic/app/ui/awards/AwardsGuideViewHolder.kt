package com.example.panoramic.app.ui.awards

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.panoramic.R
import com.example.panoramic.remote.model.Items
import com.squareup.picasso.Picasso

class AwardsGuideViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_awards_guide, parent, false)) {
    private var mModelView: TextView? = null
    private var mInchView: TextView? = null
    private var mImageView: ImageView? = null
    private var mScoreView: TextView? = null
    private var mArrow: ImageView? = null
    private var mSize: TextView? = null
    private var mType: TextView? = null
    private var mPanel: TextView? = null
    private var mHtmi: TextView? = null
    private var mUsb: TextView? = null
    private var mOs: TextView? = null
    private var mPrice: TextView? = null
    private var viewList: List<View>? = null


    init {
        mModelView = itemView.findViewById(R.id.model_number)
        mInchView = itemView.findViewById(R.id.inch_value)
        mImageView = itemView.findViewById(R.id.tv_image)
        mScoreView = itemView.findViewById(R.id.score)
        mArrow = itemView.findViewById(R.id.arrows)
        mSize = itemView.findViewById(R.id.size_value)
        mType = itemView.findViewById(R.id.type_value)
        mPanel = itemView.findViewById(R.id.panel_type_value)
        mHtmi = itemView.findViewById(R.id.HTMI_value)
        mUsb = itemView.findViewById(R.id.USB_value)
        mOs = itemView.findViewById(R.id.OS_value)
        mPrice = itemView.findViewById(R.id.price_value)
        viewList = listOf(
            itemView.findViewById(R.id.size),
            itemView.findViewById(R.id.size_value),
            itemView.findViewById(R.id.type),
            itemView.findViewById(R.id.type_value),
            itemView.findViewById(R.id.panel_type),
            itemView.findViewById(R.id.panel_type_value),
            itemView.findViewById(R.id.HTMI),
            itemView.findViewById(R.id.HTMI_value),
            itemView.findViewById(R.id.USB),
            itemView.findViewById(R.id.USB_value),
            itemView.findViewById(R.id.OS),
            itemView.findViewById(R.id.OS_value),
            itemView.findViewById(R.id.line_breaker),
            itemView.findViewById(R.id.price),
            itemView.findViewById(R.id.price_value)
        )
    }

    fun bind(product: Items, action: OnAwardItemClickListener) {
        mModelView?.text = product.model_name
        mInchView?.text = product.size.toString()
        if(product.prof_pic.isNotEmpty()) {
            Picasso.get()
                .load(product.prof_pic)
                .placeholder(R.drawable.placeholder_image)
                .into(mImageView)
        }
        mScoreView?.text = product.score.toString()
        mSize?.text = product.size.toString()
        mType?.text = product.type
        mPanel?.text = product.panel
        mHtmi?.text = product.hdmi_ports.toString()
        mUsb?.text = product.usb_ports.toString()
        mOs?.text = product.android_version
        mPrice?.text = product.price.toString()

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