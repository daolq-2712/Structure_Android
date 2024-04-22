package com.sun.data.repository

import android.util.Log
import com.sun.data.source.local.entities.toEntity
import com.sun.data.source.MovieDataSource
import com.sun.data.source.remote.model.toEntity
import com.sun.data.source.remote.model.toLocal
import com.sun.domain.entities.Movie
import com.sun.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import java.io.IOException

class MovieRepositoryImpl(
    private val remote: MovieDataSource.Remote,
    private val local: MovieDataSource.Local
) : KoinComponent, MovieRepository {

    override suspend fun getMovies(): Flow<List<Movie>> = flow {
        val movies = arrayListOf<Movie>()
        try {
            val movieRemotes = remote.getMovies().data
            movies.addAll(movieRemotes.map { it.toEntity() })
            // save to locals
            local.updateMovies(movieRemotes.map { it.toLocal() })
        } catch (e: IOException) {
            movies.addAll(local.getMoviesLocal().map {
                it.toEntity()
            })
            Log.e("MovieRepository", "getMovies failed, using local data \n Detail error:\n $e")
        }
        emit(movies)
    }

    override suspend fun getDetailMovies(movieId: Int): Flow<Movie> = flow {
        try {
            emit(local.getMovieDetailLocal(movieId).toEntity())
        } catch (e: IOException) {
            Log.e("MovieRepository", "getDetailMovies failed, retry with network \n Detail error:\n $e")
            val movieRemote = remote.getMovieDetail(movieId = movieId)
            local.updateMovies(arrayListOf(movieRemote.toLocal()))
            emit(movieRemote.toEntity())
        }
    }
}
