package com.example.panoramic.remote.model

data class TransactionDto(
    val items: List<Item>,
    val success: Boolean
)

data class Item(
    val code: String,
    val date: String,
    val price: String,
    val score_price: String
)