package com.sun.android.ui.listmovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sun.android.utils.LogUtils
import com.sun.android.utils.livedata.SingleLiveData
import com.sun.domain.entities.Movie
import com.sun.domain.usecase.GetMovieListUseCase
import kotlinx.coroutines.launch

class MoviesViewModel(private val getMovieListUseCase: GetMovieListUseCase) : ViewModel() {
    val movies = SingleLiveData<List<Movie>>()

    fun requestTopRateMovies() {
        viewModelScope.launch {
            getMovieListUseCase(GetMovieListUseCase.Input()) {
                onSuccess {
                    movies.value = it
                }
                onError {
                    LogUtils.e("requestTopRateMovies", it.toString())
                }
            }
        }
    }
}
