package dev.vaibhav.newsapp.utils.flows

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

actual class CommonMutableStateFlow<T> actual constructor(
    private val flow: MutableStateFlow<T>
) : CommonStateFlow<T>(flow), MutableStateFlow<T> {

    override var value: T
        get() = flow.value
        set(value) {
            flow.value = value
        }

    override val subscriptionCount: StateFlow<Int>
        get() = flow.subscriptionCount

    override suspend fun emit(value: T) = flow.emit(value)

    @ExperimentalCoroutinesApi
    override fun resetReplayCache() = flow.resetReplayCache()

    override fun tryEmit(value: T) = flow.tryEmit(value)

    override fun compareAndSet(expect: T, update: T) = flow.compareAndSet(expect, update)
}