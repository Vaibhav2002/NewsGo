package dev.vaibhav.newsapp.data.repo

import dev.vaibhav.newsapp.data.local.ArticleLocalDataSource
import dev.vaibhav.newsapp.data.models.mapper.toArticlesEntities
import dev.vaibhav.newsapp.data.models.remote.ArticleDto
import dev.vaibhav.newsapp.data.remote.dataSource.news.NewsRemoteDataSource
import dev.vaibhav.newsapp.domain.mappers.toArticle
import dev.vaibhav.newsapp.domain.mappers.toArticles
import dev.vaibhav.newsapp.domain.models.Article
import dev.vaibhav.newsapp.domain.models.Topic
import dev.vaibhav.newsapp.domain.models.allTopics
import dev.vaibhav.newsapp.domain.repo.NewsRepo
import dev.vaibhav.newsapp.domain.repo.SavedNewsRepo
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.supervisorScope

class NewsRepoImpl(
    private val dataSource: NewsRemoteDataSource,
    private val localDataSource: ArticleLocalDataSource,
    private val savedArticleRepo: SavedNewsRepo
) : NewsRepo {

    override val articles = combine(
        localDataSource.articles,
        savedArticleRepo.savedArticle
    ) { articles, saved ->
        val savedMap = saved.associate { it.url to it.saved!! }
        articles.toArticles(savedMap)
    }

    override suspend fun fetchAllArticles() {
        supervisorScope {
            allTopics
                .map { async { fetchTopHeadlines("in", it) } }
                .awaitAll()
        }
    }

    override suspend fun fetchTopHeadlines(country: String, topic: Topic) {
        dataSource.getTopHeadlines(country, topic.topic).also { reSaveArticles(it, topic) }
    }

    private suspend fun reSaveArticles(articles: List<ArticleDto>, topic: Topic) {
        localDataSource.deleteArticleByTopic(topic.topic)
        localDataSource.insertArticles(articles.toArticlesEntities(topic))
    }

    override suspend fun getArticleByUrl(url: String): Article {
        return localDataSource.getArticleByUrl(url).let {
            it.toArticle(saved = savedArticleRepo.getSaved(it))
        }
    }

    override suspend fun searchNews(query: String): List<Article> {
        return dataSource.getNewsByQuery(query).map { it.toArticle() }
    }
}
