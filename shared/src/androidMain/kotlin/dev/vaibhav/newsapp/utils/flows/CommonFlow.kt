package dev.vaibhav.newsapp.utils.flows

import kotlinx.coroutines.flow.Flow

actual class CommonFlow<T> actual constructor(flow: Flow<T>) : Flow<T> by flow