package com.sun.android.data.repository

import com.sun.android.data.MovieRepository
import com.sun.android.data.model.Movie
import com.sun.android.data.source.MovieDataSource
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent

class MovieRepositoryImpl(
    private val remote: MovieDataSource.Remote
) : KoinComponent, MovieRepository {

    override fun getMovies() = flow {
        emit(remote.getMovies().data)
    }

    override fun getDetailMovies(movieId: Int) = flow<Movie> {
        emit(remote.getMovieDetail(movieId = movieId))
    }
}
