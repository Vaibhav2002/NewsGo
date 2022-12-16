package dev.vaibhav.newsapp.utils.flows

import kotlinx.coroutines.flow.MutableStateFlow

expect class CommonMutableStateFlow<T>(flow:MutableStateFlow<T>):MutableStateFlow<T>

fun <T> MutableStateFlow<T>.toCommonMutableStateFlow(flow: MutableStateFlow<T>) = CommonMutableStateFlow(flow)