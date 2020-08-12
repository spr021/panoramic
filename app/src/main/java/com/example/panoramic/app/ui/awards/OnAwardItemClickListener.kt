package com.example.panoramic.app.ui.awards

import android.view.View
import com.example.panoramic.remote.model.Items

interface OnAwardItemClickListener {
    fun onItemClick(awardGuideEntity: Items, position: Int, view: View)
}