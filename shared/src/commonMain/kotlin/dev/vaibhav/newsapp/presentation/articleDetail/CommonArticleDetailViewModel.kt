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
import kotlinx.coroutines.launch

class CommonArticleDetailViewModel(
    article: Article,
    private val saveArticleUseCase: SaveArticleUseCase,
    savedNewsRepo: SavedNewsRepo,
    scope: CoroutineScope? = null
) {

    private val viewModelScope = scope ?: CoroutineScope(Dispatchers.Main)

    private val _article = MutableStateFlow(article)

    val uiState = _article
        .combine(savedNewsRepo.savedArticle) { article, saved ->
            val articleSaved = saved.find { it.url == article.url }?.saved
            article.copy(saved = articleSaved)
        }
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