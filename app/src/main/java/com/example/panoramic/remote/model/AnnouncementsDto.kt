package com.example.panoramic.remote.model

data class AnnouncementsDto(
    val items: List<Notif>,
    val success: Boolean
)

data class Notif(
    val title: String,
    val pic: String,
    val date: String,
    val seen: Int,
    val text: String,
    val id: Int
)

data class SendAnnouncementDto(
    val success: Boolean
)

data class SendAnnouncementBody(
    val id: Int,
    val cookie: String
)