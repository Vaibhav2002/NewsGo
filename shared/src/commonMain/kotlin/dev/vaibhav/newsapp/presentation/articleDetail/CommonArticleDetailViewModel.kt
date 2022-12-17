package dev.vaibhav.newsapp.presentation.articleDetail

import dev.vaibhav.newsapp.domain.repo.NewsRepo
import dev.vaibhav.newsapp.utils.DateTimeUtil
import dev.vaibhav.newsapp.utils.flows.toCommonStateFlow
import dev.vaibhav.newsapp.utils.flows.toStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest

class CommonArticleDetailViewModel(
    private val articleId: Long,
    private val newsRepo: NewsRepo,
    scope: CoroutineScope? = null
) {

    private val viewModelScope = scope ?: CoroutineScope(Dispatchers.Main)

    private val article = flow { emit(newsRepo.getArticleById(articleId)) }
        .catch { }

    val uiState = article.mapLatest {
        ArticleDetailScreenState(
            image = it.urlToImage,
            url = it.url,
            title = it.title,
            description = it.description,
            content = it.description,
            timeStamp = DateTimeUtil.formatDateTime(it.timeStamp),
        )
    }
        .toStateFlow(viewModelScope, ArticleDetailScreenState())
        .toCommonStateFlow()

}