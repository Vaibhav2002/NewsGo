package dev.vaibhav.newsapp.presentation.home

import dev.vaibhav.newsapp.domain.models.Article
import dev.vaibhav.newsapp.domain.models.Topic
import dev.vaibhav.newsapp.domain.repo.NewsRepo
import dev.vaibhav.newsapp.domain.usecases.SaveArticleUseCase
import dev.vaibhav.newsapp.utils.flows.enableLoading
import dev.vaibhav.newsapp.utils.flows.toCommonStateFlow
import dev.vaibhav.newsapp.utils.flows.toStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CommonHomeViewModel(
    private val newsRepo: NewsRepo,
    private val saveArticleUseCase: SaveArticleUseCase,
    scope: CoroutineScope?
) {
    private val viewModelScope = scope ?: CoroutineScope(Dispatchers.Main)

    private val topic = MutableStateFlow<Topic>(Topic.Headlines)

    private val isLoading = MutableStateFlow(false)
    private val isRefreshing = MutableStateFlow(false)

    private val error = MutableStateFlow<String?>(null)

    private val news = topic.flatMapLatest { topic ->
        newsRepo.articles.map { it.filter { it.topic == topic } }
    }

    val uiState = combine(
        topic,
        isLoading,
        news,
        error,
        isRefreshing
    ) { topic, loading, articles, error, refreshing ->
        HomeScreenState(
            articles = articles,
            topic = topic,
            isLoading = loading,
            error = error,
            isRefreshing = refreshing
        )
    }
        .toStateFlow(viewModelScope, HomeScreenState())
        .toCommonStateFlow()

    init {
        fetchAllArticles()
    }

    private fun fetchAllArticles() = flow { emit(newsRepo.fetchAllArticles()) }
        .enableLoading(isLoading)
        .launchIn(viewModelScope)


    fun onTopicChange(topic: Topic) {
        this.topic.update { topic }
    }

    fun onSavePress(article: Article) = viewModelScope.launch {
        saveArticleUseCase(article)
    }

    fun onRefresh() = flow {
        emit(newsRepo.fetchTopHeadlines(topic = topic.value))
    }
        .enableLoading(isRefreshing)
        .launchIn(viewModelScope)
}