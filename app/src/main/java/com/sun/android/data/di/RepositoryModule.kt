package com.sun.android.data.di

import com.sun.android.data.TokenRepository
import com.sun.android.data.repository.TokenRepositoryImpl
import com.sun.android.data.source.TokenDataSource
import org.koin.dsl.module

val RepositoryModule = module {
    single { provideTokenRepository(get()) }
}

fun provideTokenRepository(local: TokenDataSource.Local): TokenRepository {
    return TokenRepositoryImpl(local)
}
