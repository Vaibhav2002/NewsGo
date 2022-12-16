package dev.vaibhav.newsapp.android.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vaibhav.newsapp.android.domain.util.enableLoading
import dev.vaibhav.newsapp.android.domain.util.onIo
import dev.vaibhav.newsapp.android.domain.util.safeCatch
import dev.vaibhav.newsapp.domain.Topic
import dev.vaibhav.newsapp.domain.repo.NewsRepo
import dev.vaibhav.newsapp.presentation.home.CommonHomeViewModel
import dev.vaibhav.newsapp.presentation.home.HomeScreenState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(newsRepo: NewsRepo) : ViewModel() {

    private val viewModel = CommonHomeViewModel(newsRepo, viewModelScope)

    val uiState = viewModel.uiState
    fun onTopicChange(topic: Topic) = viewModel.onTopicChange(topic)
}