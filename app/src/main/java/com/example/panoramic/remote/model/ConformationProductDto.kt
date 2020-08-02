package com.example.panoramic.remote.model

data class ConformationProductDto(
    val success: Boolean
)

data class ConformationProductBody(
    val customer_name: String,
    val customer_number: String,
    val product_key: String,
    val cookie: String,
    val product_id: String
)

