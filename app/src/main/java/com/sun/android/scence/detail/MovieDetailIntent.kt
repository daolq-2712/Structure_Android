package com.sun.android.scence.detail

sealed class MovieDetailIntent {
    data class FetchMovieDetail(val movieId: Int) : MovieDetailIntent()
}
