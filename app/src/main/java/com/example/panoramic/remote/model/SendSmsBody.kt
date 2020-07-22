package com.example.panoramic.remote.model


data class SendSmsBody(
    val cookie: String?,
    val digit_code: String
)