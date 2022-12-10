package dev.vaibhav.newsapp.android.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vaibhav.newsapp.android.domain.util.enableLoading
import dev.vaibhav.newsapp.android.domain.util.onIo
import dev.vaibhav.newsapp.android.domain.util.safeCatch
import dev.vaibhav.newsapp.domain.Topic
import dev.vaibhav.newsapp.domain.repo.NewsRepo
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val newsRepo: NewsRepo): ViewModel(){

    private val topic = MutableStateFlow<Topic>(Topic.Headlines)

    private val isLoading = MutableStateFlow(false)

    private val news = topic.flatMapLatest {
        val articles = if(it is Topic.Headlines) newsRepo.getTopHeadlines("in")
        else newsRepo.getNewsByTopic(it)
        flowOf(Result.success(articles))
    }.enableLoading(isLoading).safeCatch { emit(Result.failure(it)) }.onIo()

    val uiState = combine(topic, isLoading, news){ topic, loading, articles->
        HomeScreenState(
            articles = articles.getOrDefault(emptyList()),
            topic = topic,
            isLoading = loading,
            error = articles.exceptionOrNull()?.message
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), HomeScreenState())

    fun onTopicChange(topic:Topic) {
        this.topic.update { topic }
    }
}