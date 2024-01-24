package com.sun.android.data.di

import com.sun.android.data.MovieRepository
import com.sun.android.data.TokenRepository
import com.sun.android.data.repository.MovieRepositoryImpl
import com.sun.android.data.repository.TokenRepositoryImpl
import com.sun.android.data.source.MovieDataSource
import com.sun.android.data.source.TokenDataSource
import com.sun.android.data.source.remote.MovieRemoteImpl
import org.koin.dsl.module

val RepositoryModule = module {
    single { provideTokenRepository(get()) }

    single { provideMovieRepository(MovieRemoteImpl(get())) }
}

fun provideTokenRepository(local: TokenDataSource.Local): TokenRepository {
    return TokenRepositoryImpl(local)
}

fun provideMovieRepository(remote: MovieDataSource.Remote): MovieRepository {
    return MovieRepositoryImpl(remote)
}
