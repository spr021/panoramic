package com.example.panoramic.remote.service

import com.example.panoramic.remote.NetworkManager
import com.example.panoramic.remote.model.UserInfoResponseDto
import com.google.gson.Gson

class UserInfoService(private val networkManager: NetworkManager) {

    fun getUserInfoResponse(): UserInfoResponseDto {
        val rawData = networkManager.get(URL_FEATURED)
        return Gson().fromJson(rawData, UserInfoResponseDto::class.java)
    }

    companion object{
        const val URL_FEATURED = "https://restapis.xyz/around-me/v1/featured"
    }
}