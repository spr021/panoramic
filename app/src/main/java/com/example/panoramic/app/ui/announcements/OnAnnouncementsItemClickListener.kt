package com.example.panoramic.app.ui.announcements

import com.example.panoramic.data.entity.AnnouncementsEntity

interface OnAnnouncementsItemClickListener {
    fun onItemClick(announcementsEntity: AnnouncementsEntity, position: Int)
}