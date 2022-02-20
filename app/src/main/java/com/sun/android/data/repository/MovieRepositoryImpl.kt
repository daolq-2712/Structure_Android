package com.sun.android.data.repository

import com.sun.android.base.BaseRepository
import com.sun.android.data.MovieRepository
import com.sun.android.data.source.MovieDataSource

class MovieRepositoryImpl constructor(
    private val remote: MovieDataSource.Remote
) : BaseRepository(), MovieRepository {

    override suspend fun getMovies() = withResultContext {
        remote.getMovies().data
    }

    override suspend fun getDetailMovies(movieId: Int) = withResultContext {
        remote.getMovieDetail(movieId = movieId)
    }
}
