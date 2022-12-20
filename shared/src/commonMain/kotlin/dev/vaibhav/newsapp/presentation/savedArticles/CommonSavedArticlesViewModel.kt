package dev.vaibhav.newsapp.presentation.savedArticles

import dev.vaibhav.newsapp.domain.models.Article
import dev.vaibhav.newsapp.domain.repo.SavedNewsRepo
import dev.vaibhav.newsapp.utils.flows.toStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch

class CommonSavedArticlesViewModel(
    private val savedNewsRepo: SavedNewsRepo,
    scope:CoroutineScope? = null
) {

    private val viewModelScope = scope ?: CoroutineScope(Dispatchers.Main)

    private val savedArticles = savedNewsRepo.savedArticle

    val uiState = savedArticles.mapLatest {
        SavedArticlesScreenState(articles = it)
    }.toStateFlow(viewModelScope, SavedArticlesScreenState())

    fun onSaveToggled(article: Article) = viewModelScope.launch {
        if(article.saved?.isSaved == true)
            savedNewsRepo.unSaveArticle(article)
        else savedNewsRepo.saveArticle(article)
    }
}