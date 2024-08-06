package com.sun.structure_android.presentation.screens.home

import androidx.lifecycle.viewModelScope
import com.sun.structure_android.data.model.GenreData
import com.sun.structure_android.data.model.MovieData
import com.sun.structure_android.data.repository.MovieRepository
import com.sun.structure_android.navigation.movie.MovieDestination
import com.sun.structure_android.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel constructor(
    private val movieRepository: MovieRepository,
//    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getMovieGenres()
    }

    private fun getMovieGenres() {
        viewModelScope.launch {
            val nowPlayingMovies = movieRepository.getNowPlayingMovies()
            _uiState.update { uiState ->
                uiState.copy(nowPlayingMovies = nowPlayingMovies)
            }
            val popularMovies = movieRepository.getPopularMovies()
            _uiState.update { uiState ->
                uiState.copy(popularMovies = popularMovies)
            }
        }
    }

    private fun getNowShowingMovies(genres: List<GenreData>) {
//        launchUseCase(getNowPlayingMoviesUseCase) { movies ->
//            _uiState.update { uiState ->
//                val fullInfoMovies = movies.map { movie ->
//                    val fullInfoGenres = genres.filter { genre ->
//                        movie.genreIds.orEmpty().any { it == genre.id }
//                    }
//                    movie.copy(genres = fullInfoGenres)
//                }
//                uiState.copy(nowPlayingMovies = fullInfoMovies)
//            }
//        }
    }

    private fun getPopularMovies(genres: List<GenreData>) {
//        launchUseCase(getPopularMoviesUseCase) { movies ->
//            val moviesWithGenres = movies.map { movie ->
//                val movieGenres = genres.filter { genre ->
//                    movie.genreIds.orEmpty().any { it == genre.id }
//                }
//                movie.copy(genres = movieGenres)
//            }
//            _uiState.update { it.copy(popularMovies = moviesWithGenres.take(10)) }
//        }
    }

    fun goToMovieDetail(movie: MovieData) {
        launch { _navigator.emit(MovieDestination.MovieDetail.createRoute(movie.id.toString())) }
    }
}
