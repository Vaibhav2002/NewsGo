package dev.vaibhav.newsapp.presentation.search

import dev.vaibhav.newsapp.domain.models.Article
import dev.vaibhav.newsapp.domain.repo.NewsRepo
import dev.vaibhav.newsapp.domain.repo.SavedNewsRepo
import dev.vaibhav.newsapp.domain.usecases.SaveArticleUseCase
import dev.vaibhav.newsapp.utils.flows.enableLoading
import dev.vaibhav.newsapp.utils.flows.safeCatch
import dev.vaibhav.newsapp.utils.flows.toCommonStateFlow
import dev.vaibhav.newsapp.utils.flows.toStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CommonSearchViewModel(
    private val newsRepo: NewsRepo,
    private val savedNewsRepo: SavedNewsRepo,
    private val saveArticleUseCase: SaveArticleUseCase,
    scope:CoroutineScope?
) {

    private val viewModelScope = scope ?: CoroutineScope(Dispatchers.Main)

    private val searchQuery = MutableStateFlow("")

    private val isLoading = MutableStateFlow(false)

    private val savedArticles = savedNewsRepo.savedArticle
        .mapLatest { saved-> saved.associate { it.url to it.saved!! } }

    private val articles = searchQuery
        .mapLatest { it.trim() }
        .filterNot { it.isEmpty() }
        .distinctUntilChanged()
        .debounce(1000L)
        .onEach { isLoading.emit(true) }
        .mapLatest { newsRepo.searchNews(it) }
        .combine(savedArticles){ articles, saved->
            articles.map { it.copy(saved = saved[it.url]) }
        }
        .mapLatest { Result.success(it)}
        .safeCatch{ emit(Result.failure(it)) }
        .onEach { isLoading.emit(false) }

    val uiState = combine(searchQuery, articles, isLoading){ query, articles, loading->
        SearchScreenState(
            searchQuery = query,
            articles = articles.getOrDefault(emptyList()),
            error = articles.exceptionOrNull()?.message,
            isLoading = loading
        )
    }
        .toStateFlow(viewModelScope, SearchScreenState())
        .toCommonStateFlow()

    fun onSearchQueryChanged(query:String){
        searchQuery.update { query }
    }

    fun onSaveToggled(article:Article) = viewModelScope.launch {
        saveArticleUseCase(article)
    }

}