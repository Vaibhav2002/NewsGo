package dev.vaibhav.newsapp.data.repo

import dev.vaibhav.newsapp.data.models.mapper.toArticlesEntities
import dev.vaibhav.newsapp.data.remote.dataSource.news.NewsRemoteDataSource
import dev.vaibhav.newsapp.domain.Topic
import dev.vaibhav.newsapp.domain.mappers.toArticles
import dev.vaibhav.newsapp.domain.models.Article
import dev.vaibhav.newsapp.domain.repo.NewsRepo

class NewsRepoImpl(private val dataSource: NewsRemoteDataSource) : NewsRepo {

    override suspend fun getTopHeadlines(country: String): List<Article> {
        return dataSource.getTopHeadlines(country).toArticlesEntities(Topic.Headlines).toArticles()
    }

    override suspend fun getNewsByTopic(topic: Topic): List<Article> {
        return dataSource.getNewsByQuery(topic.topic).toArticlesEntities(topic).toArticles()
    }

    override suspend fun searchNews(query: String): List<Article> {
        return dataSource.getNewsByQuery(query).toArticlesEntities(Topic.Headlines).toArticles()
    }
}