package dev.vaibhav.newsapp.android.presentation.screens

import dev.vaibhav.newsapp.domain.Topic
import dev.vaibhav.newsapp.domain.models.Article

data class HomeScreenState(
    val articles: List<Article> = emptyList(),
    val topic:Topic = Topic.Headlines,
    val isLoading:Boolean = false,
    val error: String? = null
)