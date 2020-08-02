package com.example.panoramic.remote.model

data class TopTenSellerDto(
    val items: List<Rank>,
    val success: Boolean
)

data class Rank(
    val name: String,
    val prof_pic: String,
    val rank: Int,
    val score: String,
    val shop_name: String
    //val state: String
)