package dev.vaibhav.newsapp.presentation.articleDetail

import dev.vaibhav.newsapp.domain.models.Article
import dev.vaibhav.newsapp.domain.repo.NewsRepo
import dev.vaibhav.newsapp.domain.repo.SavedNewsRepo
import dev.vaibhav.newsapp.utils.DateTimeUtil
import dev.vaibhav.newsapp.utils.flows.toCommonStateFlow
import dev.vaibhav.newsapp.utils.flows.toStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch

class CommonArticleDetailViewModel(
    private val articleId: Long,
    private val newsRepo: NewsRepo,
    private val savedNewsRepo: SavedNewsRepo,
    scope: CoroutineScope? = null
) {

    private val viewModelScope = scope ?: CoroutineScope(Dispatchers.Main)

    private val article = newsRepo.articles.mapNotNull {
        it.find { it.id == articleId }
    }.toStateFlow(viewModelScope, null)

    val uiState = article.filterNotNull().mapLatest {
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
        article.value?.let {
            if (it.saved?.isSaved == true)
                savedNewsRepo.unSaveArticle(it)
            else savedNewsRepo.saveArticle(it)
        }
    }

}