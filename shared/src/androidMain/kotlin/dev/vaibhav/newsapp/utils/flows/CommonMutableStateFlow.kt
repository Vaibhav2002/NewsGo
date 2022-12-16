package dev.vaibhav.newsapp.utils.flows

import kotlinx.coroutines.flow.MutableStateFlow

actual class CommonMutableStateFlow<T> actual constructor(flow: MutableStateFlow<T>) :
    MutableStateFlow<T> by flow