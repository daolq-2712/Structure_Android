package com.sun.android.data

import com.sun.android.data.model.Movie
import com.sun.android.utils.DataResult

interface MovieRepository {
    suspend fun getMovies(): DataResult<List<Movie>>

    suspend fun getDetailMovies(movieId: Int): DataResult<Movie>
}
