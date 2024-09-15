package com.sun.domain.usecase.base


/**
 * For using directly on current thread, not switching.
 */
abstract class BaseDirectUseCase<in Input, out Output> {

    abstract fun buildUseCase(input: Input): Output

    operator fun invoke(input: Input): Output {
        return buildUseCase(input)
    }
}
