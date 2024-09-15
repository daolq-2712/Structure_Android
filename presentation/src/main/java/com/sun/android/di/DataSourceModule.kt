package com.sun.android.di

import com.sun.data.source.MovieDataSource
import com.sun.data.source.TokenDataSource
import com.sun.data.source.local.MovieLocalImpl
import com.sun.data.source.local.TokenLocalImpl
import com.sun.data.source.remote.MovieRemoteImpl
import org.koin.dsl.module

val DataSourceModule = module {
    single<TokenDataSource.Local> { TokenLocalImpl(get()) }

    single<MovieDataSource.Remote> {
        MovieRemoteImpl(
            get()
        )
    }

    single<MovieDataSource.Local> { MovieLocalImpl(get()) }
}
