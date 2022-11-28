package dev.vaibhav.newsapp.data.remote.repo.news

import dev.vaibhav.newsapp.data.models.ArticleDto
import dev.vaibhav.newsapp.data.remote.dataSource.news.NewsDataSource
import dev.vaibhav.newsapp.domain.Topic
import dev.vaibhav.newsapp.domain.mappers.toArticles
import dev.vaibhav.newsapp.domain.models.Article
import dev.vaibhav.newsapp.domain.repo.NewsRepo

class NewsRepoImpl(private val dataSource: NewsDataSource) : NewsRepo {

    override suspend fun getTopHeadlines(country: String): List<Article> {
        return dataSource.getTopHeadlines(country).toArticles()
    }

    override suspend fun getNewsByTopic(topic: Topic): List<Article> {
        return dataSource.getNewsByQuery(topic.topic).toArticles()
    }

    override suspend fun searchNews(query: String): List<Article> {
        return dataSource.getNewsByQuery(query).toArticles()
    }
}