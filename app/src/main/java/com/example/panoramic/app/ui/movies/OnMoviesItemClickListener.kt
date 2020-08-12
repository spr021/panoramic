package com.example.panoramic.app.ui.movies

import com.example.panoramic.remote.model.Movie

interface OnMoviesItemClickListener {
    fun onItemClick(moviesEntity: Movie, position: Int)
}