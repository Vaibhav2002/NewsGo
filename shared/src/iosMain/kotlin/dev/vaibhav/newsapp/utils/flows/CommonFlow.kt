package dev.vaibhav.newsapp.utils.flows

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

actual open class CommonFlow<T> actual constructor(flow: Flow<T>) : Flow<T> by flow {

    private fun subscribe(
        scope: CoroutineScope,
        dispatcher: CoroutineDispatcher,
        onSubscribe: (T) -> Unit
    ): DisposableHandle {
        val job = scope.launch(dispatcher) { collect(onSubscribe) }
        return DisposableHandle { job.cancel() }
    }

    fun subscribe(onSubscribe: (T) -> Unit) = subscribe(GlobalScope, Dispatchers.Main, onSubscribe)

}