package dev.vaibhav.newsapp.domain.models

import kotlinx.datetime.LocalDateTime

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val timeStamp: LocalDateTime,
    val source: Source?,
    val title: String,
    val url: String,
    val urlToImage: String
)