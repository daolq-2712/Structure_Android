package com.sun.android.data.repository

import com.sun.android.data.source.MovieDataSource

class MovieRepositoryImpl private constructor(
    private val remote: MovieDataSource.Remote,
    private val local: MovieDataSource.Local
) {

    companion object {
        private var instance: MovieRepositoryImpl? = null

        fun getInstance(remote: MovieDataSource.Remote, local: MovieDataSource.Local) =
            synchronized(this) {
                instance ?: MovieRepositoryImpl(remote, local).also { instance = it }
            }
    }
}
