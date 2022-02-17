package com.sun.android.ui

import com.sun.android.base.BaseActivity
import com.sun.android.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override val viewModel: MainViewModel by viewModel()

    override fun initialize() {
        binding.txtTest.text = "ABC"
    }
}
