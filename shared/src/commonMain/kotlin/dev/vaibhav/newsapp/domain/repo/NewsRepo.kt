package dev.vaibhav.newsapp.domain.repo

import dev.vaibhav.newsapp.domain.Topic
import dev.vaibhav.newsapp.domain.models.Article

interface NewsRepo {

    suspend fun getTopHeadlines(country:String):List<Article>

    suspend fun getNewsByTopic(topic: Topic):List<Article>

    suspend fun searchNews(query:String):List<Article>
}