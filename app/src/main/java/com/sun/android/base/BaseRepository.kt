package com.sun.android.base

import com.sun.android.utils.DataResult
import com.sun.android.utils.dispatchers.BaseDispatcherProvider
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.get

abstract class BaseRepository : KoinComponent {

    private val dispatchersProvider = get<BaseDispatcherProvider>()

    /**
     * Make template code to get DataResult return to ViewModel
     * Support for call api, get data from database
     * Handle exceptions: Convert exception to Result.Error
     * Avoid duplicate code
     *
     * Default CoroutineContext is IO for repository
     */

    protected suspend fun <R> withResultContext(
        context: CoroutineContext = dispatchersProvider.io(),
        requestBlock: suspend CoroutineScope.() -> R
    ): DataResult<R> = withContext(context) {
        return@withContext try {
            val response = requestBlock()
            DataResult.Success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            DataResult.Error(e)
        }
    }
}
