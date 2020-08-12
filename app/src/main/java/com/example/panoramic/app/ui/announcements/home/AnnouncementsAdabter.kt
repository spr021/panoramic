package com.example.panoramic.app.ui.announcements.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.panoramic.app.ui.announcements.OnAnnouncementsItemClickListener
import com.example.panoramic.data.entity.AnnouncementsEntity
import com.example.panoramic.remote.model.AnnouncementsDto
import com.example.panoramic.remote.model.Notif

class AnnouncementsAdabter(
    private val list: List<Notif>,
    var clickListener: OnAnnouncementsItemClickListener
) : RecyclerView.Adapter<AnnouncementsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AnnouncementsViewHolder(
            inflater,
            parent
        )
    }

    override fun onBindViewHolder(holder: AnnouncementsViewHolder, position: Int) {
        holder.bind(list[position], clickListener)
    }

    override fun getItemCount(): Int = list.size

}