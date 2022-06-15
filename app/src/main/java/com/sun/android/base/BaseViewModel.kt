package com.sun.android.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sun.android.data.source.remote.api.error.ErrorResponse
import com.sun.android.utils.DataResult
import com.sun.android.utils.livedata.SingleLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
    val isLoading: MutableStateFlow<Boolean> by lazy { MutableStateFlow(false) }
    val errorResponse: MutableStateFlow<ErrorResponse?> by lazy { MutableStateFlow(null) }
    private var loadingCount = 0

    protected fun <T> launchTaskSync(
        onRequest: suspend CoroutineScope.() -> DataResult<T>,
        onSuccess: (T) -> Unit = {},
        onError: (Exception) -> Unit = {},
        isShowLoading: Boolean = true
    ) = viewModelScope.launch {

        showLoading(isShowLoading)
        when (val asynchronousTasks = onRequest(this)) {
            is DataResult.Success -> onSuccess(asynchronousTasks.data)
            is DataResult.Error -> {
                onError(asynchronousTasks.exception)
                ErrorResponse.convertToRetrofitException(asynchronousTasks.exception).run {
                    getErrorResponse()?.let { errorResponse.value = it }
                }
            }
            else -> {
                /*No-op*/
            }
        }
        hideLoading(isShowLoading)
    }

    private fun showLoading(isShowLoading: Boolean) {
        if (!isShowLoading) return
        loadingCount++
        if (!isLoading.value) isLoading.value = true
    }

    private fun hideLoading(isShowLoading: Boolean) {
        if (!isShowLoading) return
        loadingCount--
        if (loadingCount <= 0) {
            // reset loadingCount
            loadingCount = 0
            isLoading.value = false
        }
    }
}
