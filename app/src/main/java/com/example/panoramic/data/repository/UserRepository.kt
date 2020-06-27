package com.example.panoramic.data.repository

import com.example.panoramic.data.entity.HomeEntity
import com.example.panoramic.data.toHomeEntity
import com.example.panoramic.remote.datasource.UserInfoRemoteDataSource

class UserRepository (private val userInfoRemoteDataSource: UserInfoRemoteDataSource) {

    fun getUserInfo(success: (List<HomeEntity>?) -> Unit) {
        Thread {
            val result = userInfoRemoteDataSource.getUserInfo()?.map { PlaceDto ->
                PlaceDto.toHomeEntity()
            }
            success(result)
        }.start()
    }
}