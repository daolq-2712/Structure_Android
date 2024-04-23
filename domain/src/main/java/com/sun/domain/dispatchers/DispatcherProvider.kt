package com.sun.domain.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DispatcherProvider : BaseDispatcherProvider {

    override fun computation(): CoroutineDispatcher = Dispatchers.Default

    override fun io(): CoroutineDispatcher = Dispatchers.IO

    override fun ui(): CoroutineDispatcher = Dispatchers.Main

    override fun unconfined(): CoroutineDispatcher = Dispatchers.Unconfined
}
