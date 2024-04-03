package com.sun.android.ui.listmovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sun.android.utils.LogUtils
import com.sun.android.utils.dispatchers.DispatcherProvider
import com.sun.android.utils.livedata.SingleLiveData
import com.sun.domain.entities.MovieEntity
import com.sun.domain.repository.MovieRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MoviesViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    val movies = SingleLiveData<List<MovieEntity>>()

    fun requestTopRateMovies() {
        viewModelScope.launch {
            movieRepository.getMovies().catch { e ->
                LogUtils.e("requestTopRateMovies", e.toString())
            }.flowOn(DispatcherProvider().io())
                .collect {
                    movies.value = it
                }
        }
    }
}
