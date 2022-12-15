package dev.vaibhav.newsapp.domain.repo

import dev.vaibhav.newsapp.domain.Topic
import dev.vaibhav.newsapp.domain.models.Article

interface NewsRepo {

    suspend fun fetchAllArticles()
    suspend fun fetchTopHeadlines(country:String)

    suspend fun fetchNewsByTopic(topic: Topic)

    suspend fun observeArticlesByTopic(topic: Topic, block:(List<Article>)->Unit)

//    suspend fun searchNews(query:String):List<Article>
}