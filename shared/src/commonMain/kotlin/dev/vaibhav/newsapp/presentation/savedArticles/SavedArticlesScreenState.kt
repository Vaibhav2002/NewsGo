package dev.vaibhav.newsapp.presentation.savedArticles

import dev.vaibhav.newsapp.domain.models.Article

data class SavedArticlesScreenState(
    val articles: List<Article> = emptyList()
)
