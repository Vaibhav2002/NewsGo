package dev.vaibhav.newsapp.android.presentation.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vaibhav.newsapp.android.presentation.screens.navigation.Screens
import dev.vaibhav.newsapp.domain.repo.NewsRepo
import dev.vaibhav.newsapp.domain.repo.SavedNewsRepo
import dev.vaibhav.newsapp.domain.usecases.SaveArticleUseCase
import dev.vaibhav.newsapp.presentation.articleDetail.CommonArticleDetailViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
    newsRepo: NewsRepo,
    savedArticleUseCase: SaveArticleUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val articleUrl = savedStateHandle.get<String>(Screens.ArticleDetail.articleArg)

    private val viewModel = CommonArticleDetailViewModel(
        articleUrl = articleUrl ?: "",
        scope = viewModelScope,
        newsRepo = newsRepo,
        saveArticleUseCase = savedArticleUseCase
    )

    val uiState = viewModel.uiState

    fun toggleSave() = viewModel.toggleSave()
}