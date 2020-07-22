package com.example.panoramic.remote.model

data class ProductInquiryDto(
    val id: String,
    val model_code: String,
    val panel: String,
    val resolution: String,
    val serial: String,
    val size: String,
    val success: Boolean,
    val type: String
)