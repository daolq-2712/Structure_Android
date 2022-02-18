package com.sun.android.ui

import android.util.Log
import androidx.lifecycle.Observer
import com.sun.android.base.BaseActivity
import com.sun.android.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override val viewModel: MainViewModel by viewModel()

    override fun initialize() {
        viewModel.requestTopRateMovies()
        viewModel.movies.observe(this, Observer {
            Log.d("1111", "" + it.size)
        })
//        supportFragmentManager
//            .beginTransaction()
//            .addToBackStack(MoviesFragment::javaClass.name)
//            .replace(R.id.layoutContainer, MoviesFragment.newInstance())
//            .commit()
    }
}
