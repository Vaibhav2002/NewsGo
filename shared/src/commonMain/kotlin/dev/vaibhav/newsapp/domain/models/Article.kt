package dev.vaibhav.newsapp.domain.models

import dev.vaibhav.newsapp.domain.Topic
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val timeStamp: LocalDateTime,
    val source: Source?,
    val title: String,
    val url: String,
    val urlToImage: String,
    val topic: Topic,
    val saved: Saved? = null
)