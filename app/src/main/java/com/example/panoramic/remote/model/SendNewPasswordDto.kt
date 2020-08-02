package com.example.panoramic.remote.model

data class SendNewPasswordDto(
    val success: Boolean
)

data class SendNewPasswordBody(
    val cookie: String?,
    val password: String,
    val _method: String = "PUT"
)