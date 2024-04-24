package com.sun.android.di

import com.sun.android.scence.MainViewModel
import com.sun.android.scence.detail.MovieDetailViewModel
import com.sun.android.scence.listmovie.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val ViewModelModule: Module = module {
    viewModel { MainViewModel() }
    viewModel { MoviesViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
}
