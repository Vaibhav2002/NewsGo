package dev.vaibhav.newsapp.presentation.search

import dev.vaibhav.newsapp.domain.models.Article

data class SearchScreenState(
    val searchQuery:String = "",
    val articles:List<Article> = emptyList(),
    val error:String? = null,
    val isLoading:Boolean = false
)
