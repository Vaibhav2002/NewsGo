package dev.vaibhav.newsapp.data.repo

import dev.vaibhav.newsapp.data.local.ArticleLocalDataSource
import dev.vaibhav.newsapp.data.models.mapper.toArticlesEntities
import dev.vaibhav.newsapp.data.models.remote.ArticleDto
import dev.vaibhav.newsapp.data.remote.dataSource.news.NewsRemoteDataSource
import dev.vaibhav.newsapp.domain.Topic
import dev.vaibhav.newsapp.domain.mappers.toArticle
import dev.vaibhav.newsapp.domain.mappers.toArticles
import dev.vaibhav.newsapp.domain.models.Article
import dev.vaibhav.newsapp.domain.repo.NewsRepo
import dev.vaibhav.newsapp.domain.repo.SavedNewsRepo
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
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
            val headlines = async { fetchTopHeadlines("in") }
            listOf(Topic.Sports, Topic.Technology, Topic.Politics, Topic.Entertainment)
                .map { async { fetchNewsByTopic(it) } }
                .toMutableList().apply { add(headlines) }
                .awaitAll()
        }
    }

    override suspend fun fetchTopHeadlines(country: String) {
        dataSource.getTopHeadlines(country).also { reSaveArticles(it, Topic.Headlines) }
    }

    override suspend fun fetchNewsByTopic(topic: Topic) {
        dataSource.getNewsByQuery(topic.topic).also { reSaveArticles(it, topic) }
    }

    private suspend fun reSaveArticles(articles: List<ArticleDto>, topic: Topic) {
        localDataSource.deleteArticleByTopic(topic.topic)
        localDataSource.insertArticles(articles.toArticlesEntities(topic))
    }

    override suspend fun getArticleById(id: Long): Article {
        return localDataSource.getArticleById(id).let {
            it.toArticle(saved = savedArticleRepo.getSaved(it))
        }
    }

}
