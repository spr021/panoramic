package com.example.panoramic.app

import android.content.Context
import com.example.panoramic.data.repository.UserRepository
import com.example.panoramic.remote.NetworkManager
import com.example.panoramic.remote.datasource.UserInfoRemoteDataSource
import com.example.panoramic.remote.service.UserInfoService

object Injector {

    fun provideUserRepository(context: Context): UserRepository {
        return UserRepository(
            UserInfoRemoteDataSource(
                UserInfoService(
                    NetworkManager()
                )
            )
        )
    }
}