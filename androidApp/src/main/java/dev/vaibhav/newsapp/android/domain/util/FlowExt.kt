package dev.vaibhav.newsapp.android.domain.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
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