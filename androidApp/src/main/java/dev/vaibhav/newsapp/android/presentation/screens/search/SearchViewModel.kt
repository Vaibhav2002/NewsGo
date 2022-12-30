package dev.vaibhav.newsapp.android.presentation.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vaibhav.newsapp.domain.models.Article
import dev.vaibhav.newsapp.domain.repo.NewsRepo
import dev.vaibhav.newsapp.domain.repo.SavedNewsRepo
import dev.vaibhav.newsapp.domain.usecases.SaveArticleUseCase
import dev.vaibhav.newsapp.presentation.search.CommonSearchViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    newsRepo: NewsRepo,
    savedNewsRepo: SavedNewsRepo,
    saveArticleUseCase: SaveArticleUseCase
) : ViewModel() {

    private val viewModel = CommonSearchViewModel(
        newsRepo = newsRepo,
        savedNewsRepo = savedNewsRepo,
        saveArticleUseCase = saveArticleUseCase,
        scope = viewModelScope
    )

    val uiState = viewModel.uiState

    fun onSearchQueryChange(query: String) = viewModel.onSearchQueryChanged(query)

    fun onSaveToggled(article: Article) = viewModel.onSaveToggled(article)
}