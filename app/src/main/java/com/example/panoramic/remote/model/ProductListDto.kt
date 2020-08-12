package com.example.panoramic.remote.model

data class ProductListDto(
    val items: List<Items>,
    val success: Boolean
)

data class Items(
    val android_version: String,
    val brand: String,
    val color: String,
    val company: String,
    val hdmi_ports: Int,
    val id: Int,
    val model_name: String,
    val panel: String,
    val price: Int,
    val prof_pic: String,
    val resolution: String,
    val score: Int,
    val size: Int,
    val type: String,
    val usb_ports: Int,
    val year: Int
)