package com.sun.android.di

import com.sun.domain.usecase.GetMovieListUseCase
import org.koin.dsl.module

val UseCaseModule = module {
    factory { GetMovieListUseCase(get()) }
}
