package com.sun.android.scence.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sun.android.data.MovieRepository
import com.sun.android.data.model.Movie
import com.sun.android.utils.LogUtils
import com.sun.android.utils.livedata.SingleLiveData
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    val movie = SingleLiveData<Movie>()

    val movieDetailIntent = Channel<MovieDetailIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<MovieDetailState>(MovieDetailState.Idle)
    val state: StateFlow<MovieDetailState>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            movieDetailIntent.consumeAsFlow().collect {
                when (it) {
                    is MovieDetailIntent.FetchMovieDetail -> requestMovieDetails(it.movieId)
                }
            }
        }
    }

    fun requestMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _state.value = MovieDetailState.Loading
            movieRepository.getDetailMovies(movieId).catch {
                LogUtils.e("QQQQQ", it.toString())
                _state.value = MovieDetailState.Error(it.toString())
            }.collect {
                movie.value = it
                _state.value = MovieDetailState.MovieData(it)
            }
        }
    }
}
