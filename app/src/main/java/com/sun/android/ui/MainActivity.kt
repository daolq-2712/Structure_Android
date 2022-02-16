package com.sun.android.ui

import com.sun.android.base.BaseActivity
import com.sun.android.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override val viewModel get() = MainViewModel()

    override fun initialize() {
        binding.txtTest.text = "ABC"
    }
}
