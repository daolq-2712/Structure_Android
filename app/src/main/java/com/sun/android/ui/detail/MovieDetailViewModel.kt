package com.sun.android.ui.detail

import com.sun.android.base.BaseViewModel
import com.sun.android.data.MovieRepository
import com.sun.android.data.model.Movie
import com.sun.android.utils.livedata.SingleLiveData

class MovieDetailViewModel(private val movieRepository: MovieRepository) : BaseViewModel() {
    val movie = SingleLiveData<Movie>()

    fun requestMovieDetails(movieId: Int) {
        launchTaskSync(onRequest = {
            movieRepository.getDetailMovies(movieId)
        },
            onSuccess = {
                movie.value = it
            })
    }
}
