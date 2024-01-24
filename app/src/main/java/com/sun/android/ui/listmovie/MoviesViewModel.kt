package com.sun.android.ui.listmovie

import com.sun.android.base.BaseViewModel
import com.sun.android.data.MovieRepository
import com.sun.android.data.model.Movie
import com.sun.android.utils.LogUtils
import com.sun.android.utils.livedata.SingleLiveData

class MoviesViewModel(private val movieRepository: MovieRepository) : BaseViewModel() {
    val movies = SingleLiveData<List<Movie>>()

    fun requestTopRateMovies() {
        launchTaskSync(onRequest = {
            movieRepository.getMovies()
        }, onSuccess = {
            movies.value = it
            LogUtils.e("QQQQQ", movies.toString())
        }, onError = {
            LogUtils.e("QQQQQ", it.toString())
            // No-op
        })
    }
}
