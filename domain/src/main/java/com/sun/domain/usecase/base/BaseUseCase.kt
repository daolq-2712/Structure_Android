package com.sun.domain.usecase.base

import com.sun.domain.dispatchers.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

abstract class BaseUseCase<in Input, out Output> {

    protected abstract suspend fun buildUseCase(input: Input): Flow<Output>

    suspend operator fun invoke(input: Input, block: BaseObserver<out Output>.() -> Unit) {
        val response = BaseObserver<Output>().apply { block() }
        buildUseCase(input).catch {
            response(it)
        }.flowOn(DispatcherProvider().io()).collect {
            response(it)
        }
    }
}
