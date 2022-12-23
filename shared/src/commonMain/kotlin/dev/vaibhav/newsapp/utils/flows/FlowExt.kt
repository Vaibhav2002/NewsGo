package dev.vaibhav.newsapp.utils.flows

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

fun <T> Flow<T>.toStateFlow(
    coroutineScope: CoroutineScope,
    defaultValue: T
) = stateIn(coroutineScope, SharingStarted.WhileSubscribed(5000L), defaultValue)