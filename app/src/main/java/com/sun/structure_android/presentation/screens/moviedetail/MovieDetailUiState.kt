package com.sun.structure_android.presentation.screens.moviedetail

import com.sun.structure_android.data.model.CastData
import com.sun.structure_android.data.model.MovieData

data class MovieDetailUiState(
    val movie: MovieData? = null,
    val hasBookmark: Boolean = false,
    val casts: List<CastData> = emptyList(),
)
