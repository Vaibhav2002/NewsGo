package dev.vaibhav.newsapp.android.presentation.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vaibhav.newsapp.android.presentation.screens.navigation.Screens
import dev.vaibhav.newsapp.domain.repo.NewsRepo
import dev.vaibhav.newsapp.domain.repo.SavedNewsRepo
import dev.vaibhav.newsapp.presentation.articleDetail.CommonArticleDetailViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
    newsRepo:NewsRepo,
    savedNewsRepo: SavedNewsRepo,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val articleId = savedStateHandle.get<Long>(Screens.ArticleDetail.articleArg)

    private val viewModel = CommonArticleDetailViewModel(
        articleId = articleId ?: 0,
        scope = viewModelScope,
        newsRepo = newsRepo,
        savedNewsRepo = savedNewsRepo
    )

    val uiState = viewModel.uiState

    fun toggleSave() = viewModel.toggleSave()
}