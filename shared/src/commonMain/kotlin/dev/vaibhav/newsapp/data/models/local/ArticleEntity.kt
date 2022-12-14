package dev.vaibhav.newsapp.data.models.local

data class ArticleEntity(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: String?,
    val title: String,
    val url: String,
    val urlToImage: String,
    val topic:String
)
