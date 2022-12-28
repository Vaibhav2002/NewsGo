package dev.vaibhav.newsapp.presentation.articleDetail

import dev.vaibhav.newsapp.domain.models.Article
import dev.vaibhav.newsapp.domain.repo.SavedNewsRepo
import dev.vaibhav.newsapp.domain.usecases.SaveArticleUseCase
import dev.vaibhav.newsapp.utils.DateTimeUtil
import dev.vaibhav.newsapp.utils.flows.toCommonStateFlow
import dev.vaibhav.newsapp.utils.flows.toStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch

class CommonArticleDetailViewModel(
    article: Article,
    private val saveArticleUseCase: SaveArticleUseCase,
    savedNewsRepo: SavedNewsRepo,
    scope: CoroutineScope? = null
) {

    private val viewModelScope = scope ?: CoroutineScope(Dispatchers.Main)

    private val saved = savedNewsRepo.savedArticle.mapLatest { savedList ->
        savedList.find { it.url == article.url }?.saved
    }

    private val _article = saved.mapLatest {
        article.copy(saved = it)
    }.toStateFlow(viewModelScope, article)

    val uiState = _article
        .map {
            ArticleDetailScreenState(
                image = it.urlToImage,
                url = it.url,
                title = it.title,
                description = it.description,
                content = it.description,
                timeStamp = DateTimeUtil.formatDateTime(it.timeStamp),
                isSaved = it.saved?.isSaved == true
            )
        }
        .toStateFlow(viewModelScope, ArticleDetailScreenState())
        .toCommonStateFlow()

    fun toggleSave() = viewModelScope.launch {
        saveArticleUseCase(_article.value)
    }
}