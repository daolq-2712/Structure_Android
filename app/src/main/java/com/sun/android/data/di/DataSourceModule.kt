package com.sun.android.data.di

import com.sun.android.data.source.MovieDataSource
import com.sun.android.data.source.TokenDataSource
import com.sun.android.data.source.local.TokenLocalImpl
import com.sun.android.data.source.remote.MovieRemoteImpl
import org.koin.dsl.module

val DataSourceModule = module {
    single<TokenDataSource.Local> { TokenLocalImpl(get()) }

    single<MovieDataSource.Remote> { MovieRemoteImpl(get()) }
}
