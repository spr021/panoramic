package com.example.panoramic.remote.model

data class MovieDto(
    val items: List<Movie>,
    val success: Boolean
)

data class Movie(
    val title: String,
    val film_pic: String,
    val time: String,
    val seen: Int,
    val text: String,
    val id: Int,
    val link: String
)

data class SendMovieDto(
    val success: Boolean
)

data class SendMovieBody(
    val entity_id: Int,
    val cookie: String
)