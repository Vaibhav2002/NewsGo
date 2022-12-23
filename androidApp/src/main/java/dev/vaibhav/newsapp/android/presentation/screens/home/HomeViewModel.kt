package dev.vaibhav.newsapp.android.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vaibhav.newsapp.domain.models.Article
import dev.vaibhav.newsapp.domain.models.Topic
import dev.vaibhav.newsapp.domain.repo.NewsRepo
import dev.vaibhav.newsapp.domain.usecases.SaveArticleUseCase
import dev.vaibhav.newsapp.presentation.home.CommonHomeViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    newsRepo: NewsRepo,
    savedArticleUseCase: SaveArticleUseCase
) : ViewModel() {

    private val viewModel = CommonHomeViewModel(newsRepo, savedArticleUseCase, viewModelScope)

    val uiState = viewModel.uiState
    fun onTopicChange(topic: Topic) = viewModel.onTopicChange(topic)

    fun onSaveClick(article: Article) = viewModel.onSavePress(article)

    fun onRefresh() = viewModel.onRefresh()
}