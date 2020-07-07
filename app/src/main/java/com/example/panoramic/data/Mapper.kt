package com.example.panoramic.data

import com.example.panoramic.data.entity.HomeEntity
import com.example.panoramic.remote.model.PlaceDto

fun PlaceDto.toHomeEntity() = HomeEntity(
    title = full_name,
    time = lng,
    image = name,
    seen = is_verified
)
