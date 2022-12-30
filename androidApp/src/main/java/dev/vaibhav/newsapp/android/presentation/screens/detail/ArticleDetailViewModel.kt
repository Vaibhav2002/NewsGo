package dev.vaibhav.newsapp.android.presentation.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vaibhav.newsapp.android.presentation.screens.navigation.Screens
import dev.vaibhav.newsapp.domain.models.Article
import dev.vaibhav.newsapp.domain.repo.SavedNewsRepo
import dev.vaibhav.newsapp.domain.usecases.SaveArticleUseCase
import dev.vaibhav.newsapp.presentation.articleDetail.CommonArticleDetailViewModel
import dev.vaibhav.newsapp.utils.serialize.JsonSerializer
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
    savedNewsRepo: SavedNewsRepo,
    savedArticleUseCase: SaveArticleUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val article: Article? = savedStateHandle.get<String>(Screens.ArticleDetail.articleArg)
        ?.let(JsonSerializer::deserialize)

    private val viewModel = CommonArticleDetailViewModel(
        article = article!!,
        scope = viewModelScope,
        savedNewsRepo = savedNewsRepo,
        saveArticleUseCase = savedArticleUseCase
    )

    val uiState = viewModel.uiState

    fun toggleSave() = viewModel.toggleSave()
}