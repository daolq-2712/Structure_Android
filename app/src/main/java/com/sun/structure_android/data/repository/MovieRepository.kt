package com.sun.structure_android.data.repository

import com.sun.structure_android.data.model.GenreData
import com.sun.structure_android.data.model.MovieData

interface MovieRepository {
    suspend fun getMovieGenre(): List<GenreData>

    suspend fun getNowPlayingMovies(): List<MovieData>

    suspend fun getPopularMovies(): List<MovieData>

    suspend fun getMovieDetail(movieId: Int): MovieData?
}

class MovieRepositoryImpl() : MovieRepository {
    override suspend fun getMovieGenre(): List<GenreData> {
        val genres: List<GenreData> = listOf(GenreData(0, "QQQ"), GenreData(1, "WWW"))
        return genres
    }

    override suspend fun getNowPlayingMovies(): List<MovieData> {
        val movies = listOf(MovieData(id = 0, title = "QQQ"), MovieData(id = 1,title = "WWW"), MovieData(id = 2,title = "EEE"), MovieData(id = 3,title = "RRR"))
        return movies
    }

    override suspend fun getPopularMovies(): List<MovieData> {
        val movies = listOf(MovieData(id = 0, title = "QQQ"), MovieData(id = 1,title = "WWW"), MovieData(id = 2,title = "EEE"), MovieData(id = 3,title = "RRR"))
        return movies
    }

    override suspend fun getMovieDetail(movieId: Int): MovieData? {
        return MovieData(id = 1,title = "QQQ")
    }
}
