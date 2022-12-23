package dev.vaibhav.newsapp.presentation.home

import dev.vaibhav.newsapp.domain.models.Article
import dev.vaibhav.newsapp.domain.models.Topic

data class HomeScreenState(
    val articles: List<Article> = emptyList(),
    val topic: Topic = Topic.Headlines,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isRefreshing: Boolean = false
) {
    val topics = listOf(
        Topic.Headlines, Topic.Sports, Topic.Politics,
        Topic.Entertainment, Topic.Technology
    )
}