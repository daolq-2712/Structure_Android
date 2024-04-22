package com.sun.domain.repository

import com.sun.domain.entities.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(): Flow<List<Movie>>

    suspend fun getDetailMovies(movieId: Int): Flow<Movie>
}
