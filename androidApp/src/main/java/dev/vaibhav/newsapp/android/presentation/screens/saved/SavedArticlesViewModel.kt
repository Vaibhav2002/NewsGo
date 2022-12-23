package dev.vaibhav.newsapp.android.presentation.screens.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vaibhav.newsapp.android.domain.util.onIo
import dev.vaibhav.newsapp.domain.models.Article
import dev.vaibhav.newsapp.domain.repo.SavedNewsRepo
import dev.vaibhav.newsapp.presentation.savedArticles.CommonSavedArticlesViewModel
import javax.inject.Inject

@HiltViewModel
class SavedArticlesViewModel @Inject constructor(
    savedNewsRepo: SavedNewsRepo
) : ViewModel() {

    private val viewModel = CommonSavedArticlesViewModel(
        savedNewsRepo = savedNewsRepo,
        scope = viewModelScope
    )

    val uiState = viewModel.uiState.onIo()

    fun onSaveToggled(article: Article) = viewModel.onSaveToggled(article)
}