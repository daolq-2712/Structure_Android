package com.sun.android.ui.listmovie

import com.sun.android.base.BaseFragment
import com.sun.android.databinding.MoviesFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : BaseFragment<MoviesFragmentBinding>(MoviesFragmentBinding::inflate) {

    override val viewModel: MoviesViewModel by viewModel()

    override fun initialize() {
    }
}
