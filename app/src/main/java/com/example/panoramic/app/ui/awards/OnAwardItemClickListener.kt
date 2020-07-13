package com.example.panoramic.app.ui.awards

import android.view.View
import com.example.panoramic.data.entity.AwardGuideEntity

interface OnAwardItemClickListener {
    fun onItemClick(awardGuideEntity: AwardGuideEntity, position: Int, view: View)
}