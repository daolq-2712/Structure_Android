package com.sun.android.scence.detail

import com.sun.android.data.model.Movie

sealed class MovieDetailState {
    object Idle : MovieDetailState()
    object Loading : MovieDetailState()

    data class MovieData(val movie: Movie) : MovieDetailState()
    data class Error(val error: String?) : MovieDetailState()
}
