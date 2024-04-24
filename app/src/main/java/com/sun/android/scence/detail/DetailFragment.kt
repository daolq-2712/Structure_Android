package com.sun.android.scence.detail

import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.sun.android.base.BaseFragment
import com.sun.android.databinding.FragmentDetailBinding
import com.sun.android.utils.extension.goBackFragment
import com.sun.android.utils.extension.loadImageCircleWithUrl
import com.sun.android.utils.extension.loadImageWithUrl
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    override val viewModel: MovieDetailViewModel by viewModel()

    override fun initView() {
        binding.buttonImageBack.setOnClickListener { goBackFragment() }
    }

    override fun initData() {
        arguments?.run {
            val mMovieId = getInt(ARGUMENT_MOVIE_ID, -1)
            lifecycleScope.launch {
                viewModel.movieDetailIntent.send(MovieDetailIntent.FetchMovieDetail(mMovieId))
            }
        }
    }

    override fun bindData() {
        lifecycleScope.launch {
            viewModel.state.collect {
                    when(it) {
                        is MovieDetailState.Idle -> {

                        }
                        is MovieDetailState.Loading -> {

                        }
                        is MovieDetailState.MovieData -> {
                            val movie = it.movie
                            binding.imageBackDrop.loadImageWithUrl(movie.backDropImage)
                            binding.imageMovie.loadImageCircleWithUrl(movie.urlImage)
                            binding.textTitle.text = movie.title
                            binding.textDescription.text = movie.overView
                            binding.textRatting.text = movie.vote.toString()
                            binding.textTotalReview.text = movie.voteCount.toString()
                        }
                        is MovieDetailState.Error -> {

                        }
                    }
            }
        }
    }

    companion object {
        private const val ARGUMENT_MOVIE_ID = "ARGUMENT_MOVIE_ID"

        fun newInstance(movieId: Int) = DetailFragment().apply {
            arguments = bundleOf(ARGUMENT_MOVIE_ID to movieId)
        }
    }
}
