package com.sun.structure_android.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sun.structure_android.navigation.BaseDestination
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

open class BaseViewModel : ViewModel() {

    protected val _navigator = MutableSharedFlow<BaseDestination>()
    val navigator = _navigator.asSharedFlow()

    protected fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        job: suspend () -> Unit,
    ) {
        viewModelScope.launch(context) {
            job.invoke()
        }
    }
}
