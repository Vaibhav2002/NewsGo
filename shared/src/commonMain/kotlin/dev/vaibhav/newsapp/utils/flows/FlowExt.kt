package dev.vaibhav.newsapp.utils.flows

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

fun <T> Flow<T>.toStateFlow(
    coroutineScope: CoroutineScope,
    defaultValue: T
) = stateIn(coroutineScope, SharingStarted.WhileSubscribed(5000L), defaultValue)

fun <T> Flow<T>.safeCatch(block: suspend FlowCollector<T>.(Throwable) -> Unit = {}) = catch { e ->
    block(e)
    if (e is CancellationException) throw e
}

fun <T> Flow<T>.enableLoading(
    loadingFlow: MutableStateFlow<Boolean>
) = onStart { loadingFlow.emit(true) }
    .onCompletion { loadingFlow.emit(false) }
