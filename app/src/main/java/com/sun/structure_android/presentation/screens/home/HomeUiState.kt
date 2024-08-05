package com.sun.structure_android.presentation.screens.home

import com.sun.structure_android.data.model.MovieData

data class HomeUiState(
    val nowPlayingMovies: List<MovieData> = emptyList(),
    val popularMovies: List<MovieData> = emptyList(),
)
