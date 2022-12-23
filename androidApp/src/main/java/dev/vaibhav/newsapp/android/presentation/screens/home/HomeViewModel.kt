package dev.vaibhav.newsapp.android.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vaibhav.newsapp.android.domain.util.onIo
import dev.vaibhav.newsapp.domain.Topic
import dev.vaibhav.newsapp.domain.models.Article
import dev.vaibhav.newsapp.domain.repo.NewsRepo
import dev.vaibhav.newsapp.domain.repo.SavedNewsRepo
import dev.vaibhav.newsapp.presentation.home.CommonHomeViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    newsRepo: NewsRepo,
    savedNewsRepo: SavedNewsRepo
) : ViewModel() {

    private val viewModel = CommonHomeViewModel(newsRepo, savedNewsRepo, viewModelScope)

    val uiState = viewModel.uiState.onIo()
    fun onTopicChange(topic: Topic) = viewModel.onTopicChange(topic)

    fun onSaveClick(article: Article) = viewModel.onSavePress(article)

    fun onRefresh() = viewModel.onRefresh()
}