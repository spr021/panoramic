package com.example.panoramic.app.ui.movies

import com.example.panoramic.data.entity.MoviesEntity

interface OnMoviesItemClickListener {
    fun onItemClick(moviesEntity: MoviesEntity, position: Int)
}