package com.example.panoramic.remote.model

data class ScoreDto(
    val items: List<Product>,
    val last_name: String,
    val name: String,
    val score_total: String,
    val success: Boolean,
    val prof_pic: String
)

data class Product(
    val accepted: Int,
    val created_at: String,
    val model_code: String,
    val score: String,
    val serial: String
)