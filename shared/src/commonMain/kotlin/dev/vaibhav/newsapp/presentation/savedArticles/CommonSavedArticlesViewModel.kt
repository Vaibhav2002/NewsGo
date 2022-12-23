package dev.vaibhav.newsapp.presentation.savedArticles

import dev.vaibhav.newsapp.domain.models.Article
import dev.vaibhav.newsapp.domain.repo.SavedNewsRepo
import dev.vaibhav.newsapp.domain.usecases.SaveArticleUseCase
import dev.vaibhav.newsapp.utils.flows.toCommonStateFlow
import dev.vaibhav.newsapp.utils.flows.toStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch

class CommonSavedArticlesViewModel(
    private val savedNewsRepo: SavedNewsRepo,
    private val saveArticleUseCase: SaveArticleUseCase,
    scope: CoroutineScope? = null
) {

    private val viewModelScope = scope ?: CoroutineScope(Dispatchers.Main)

    private val savedArticles = savedNewsRepo.savedArticle

    val uiState = savedArticles.mapLatest {
        SavedArticlesScreenState(articles = it)
    }
        .toStateFlow(viewModelScope, SavedArticlesScreenState())
        .toCommonStateFlow()

    fun onSaveToggled(article: Article) = viewModelScope.launch {
        saveArticleUseCase(article)
    }
}