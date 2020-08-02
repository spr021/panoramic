package com.example.panoramic.remote.model

data class ChangePasswordDto(
    val success: Boolean
)

data class ChangePasswordBody(
    val cookie: String?,
    val password: String,
    val old_password: String,
    val _method: String = "PUT"
)