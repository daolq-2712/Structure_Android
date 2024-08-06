package com.sun.structure_android.presentation.screens.moviedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sun.structure_android.data.repository.MovieRepository
import com.sun.structure_android.presentation.base.BaseViewModel
import com.sun.structure_android.shared.KEY_MOVIE_ID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailViewModel constructor(
    savedStateHandle: SavedStateHandle,
    private val movieRepository: MovieRepository,
) : BaseViewModel() {
    private val movieId: String = checkNotNull(savedStateHandle[KEY_MOVIE_ID])

    private val _uiState = MutableStateFlow(MovieDetailUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getMovieDetail()
    }

    private fun getMovieDetail() {
        viewModelScope.launch {
            val movie = movieRepository.getMovieDetail(movieId.toInt())
            _uiState.update { it.copy(movie = movie) }
        }
    }
}
