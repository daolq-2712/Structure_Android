package com.sun.domain.usecase

import com.sun.domain.entities.Movie
import com.sun.domain.repository.MovieRepository
import com.sun.domain.usecase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow

class GetMovieListUseCase(private val movieRepository: MovieRepository) :
    BaseUseCase<GetMovieListUseCase.Input, List<Movie>>() {

    class Input

    override suspend fun buildUseCase(input: Input): Flow<List<Movie>> {
        return movieRepository.getMovies()
    }
}
