package com.example.panoramic.app.ui.announcements

import com.example.panoramic.remote.model.Notif

interface OnAnnouncementsItemClickListener {
    fun onItemClick(announcementsEntity: Notif, position: Int)
}