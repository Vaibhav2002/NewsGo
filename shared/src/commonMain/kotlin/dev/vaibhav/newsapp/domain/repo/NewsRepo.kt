package dev.vaibhav.newsapp.domain.repo

import dev.vaibhav.newsapp.domain.models.Article
import dev.vaibhav.newsapp.domain.models.Topic
import kotlinx.coroutines.flow.Flow

interface NewsRepo {

    val articles: Flow<List<Article>>

    suspend fun fetchAllArticles()

    suspend fun fetchTopHeadlines(country: String = "in", topic: Topic)

    suspend fun getArticleByUrl(url: String): Article

    suspend fun searchNews(query:String):List<Article>
}