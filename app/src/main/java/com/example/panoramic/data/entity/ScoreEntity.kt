package com.example.panoramic.data.entity

data class ScoreEntity(
    val modelNumber: String,
    val serialNumber: String,
    val score: Int,
    val date: String,
    val time: String,
    val register: Boolean
)