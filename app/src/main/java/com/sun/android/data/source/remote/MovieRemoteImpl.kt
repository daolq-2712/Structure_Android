package com.sun.android.data.source.remote

import com.sun.android.data.model.Movie
import com.sun.android.data.source.MovieDataSource
import com.sun.android.data.source.remote.api.ApiService
import com.sun.android.data.source.remote.api.response.BaseResponse

class MovieRemoteImpl(private val apiService: ApiService) : MovieDataSource.Remote {
    override fun getMovies(): BaseResponse<List<Movie>> {
        TODO("Not yet implemented")
    }
}
