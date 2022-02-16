package com.sun.android.base

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding

typealias ActivityInflate<T> = (LayoutInflater) -> T

abstract class BaseActivity<VB : ViewBinding>(private val inflate: ActivityInflate<VB>) : AppCompatActivity() {
    private var _binding: VB? = null

    val binding get() = _binding!!
    abstract val viewModel: BaseViewModel

    protected abstract fun initialize()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflate.invoke(layoutInflater)
        setContentView(binding.root)

        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        initialize()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
