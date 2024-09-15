package com.sun.domain.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

interface BaseDispatcherProvider {

    fun computation(): CoroutineDispatcher

    fun io(): CoroutineDispatcher

    fun ui(): CoroutineDispatcher

    fun unconfined(): CoroutineDispatcher
}
