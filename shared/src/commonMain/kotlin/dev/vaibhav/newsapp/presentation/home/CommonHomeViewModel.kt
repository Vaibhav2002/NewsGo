package dev.vaibhav.newsapp.presentation.home

import dev.vaibhav.newsapp.domain.Topic
import dev.vaibhav.newsapp.domain.repo.NewsRepo
import dev.vaibhav.newsapp.utils.flows.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CommonHomeViewModel(
    private val newsRepo: NewsRepo,
    scope: CoroutineScope?
) {
    private val viewModelScope = scope ?: CoroutineScope(Dispatchers.Main)

    private val topic = MutableStateFlow<Topic>(Topic.Headlines)

    private val isLoading = MutableStateFlow(false)

    private val error = MutableStateFlow<String?>(null)

    private val news = topic.flatMapLatest { topic ->
        newsRepo.articles.map { it.filter { it.topic == topic } }
    }

    val uiState = combine(topic, isLoading, news, error) { topic, loading, articles, error ->
        HomeScreenState(
            articles = articles,
            topic = topic,
            isLoading = loading,
            error = error
        )
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), HomeScreenState())
        .toCommonStateFlow()

    init {
        fetchAllArticles()
    }

    private fun fetchAllArticles() = flow { emit(newsRepo.fetchAllArticles()) }
        .catch { println(it.stackTraceToString()) }
        .launchIn(viewModelScope)


    fun onTopicChange(topic: Topic) {
        this.topic.update { topic }
    }
}