package dev.vaibhav.newsapp.android.domain.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import java.util.concurrent.CancellationException

fun <T> Flow<T>.safeCatch(block: suspend FlowCollector<T>.(Throwable) -> Unit) = catch { e ->
    block(e)
    if (e is CancellationException) throw e
}

fun <T> Flow<T>.enableLoading(
    loadingFlow: MutableStateFlow<Boolean>
) = onStart { loadingFlow.emit(true) }
    .onCompletion { loadingFlow.emit(false) }

fun <T> Flow<T>.onIo() = flowOn(Dispatchers.IO)