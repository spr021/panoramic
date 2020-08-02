package com.example.panoramic.remote.model

data class SendPhoneDto(
    val message_sent: Boolean
)

data class SendPhoneBody(
    val cookie: String?,
    val phone_number: String?
)