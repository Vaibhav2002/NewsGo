package dev.vaibhav.newsapp.data.repo

import dev.vaibhav.newsapp.data.local.ArticleLocalDataSource
import dev.vaibhav.newsapp.data.models.mapper.toArticlesEntities
import dev.vaibhav.newsapp.data.models.remote.ArticleDto
import dev.vaibhav.newsapp.data.remote.dataSource.news.NewsRemoteDataSource
import dev.vaibhav.newsapp.domain.Topic
import dev.vaibhav.newsapp.domain.mappers.toArticles
import dev.vaibhav.newsapp.domain.models.Article
import dev.vaibhav.newsapp.domain.repo.NewsRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class NewsRepoImpl(
    private val dataSource: NewsRemoteDataSource,
    private val localDataSource: ArticleLocalDataSource
) : NewsRepo {

    val articles = localDataSource.articles.map { it.toArticles() }

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override suspend fun fetchAllArticles() {
        coroutineScope.launch {
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

    /**
     * For iOS as it does not support flows
     */
    override suspend fun observeArticlesByTopic(topic: Topic, block: (List<Article>) -> Unit){
        articles
            .map { it.filter { it.topic == topic } }
            .collectLatest(block)
    }


//    override suspend fun searchNews(query: String): List<Article> {
//        return dataSource.getNewsByQuery(query).toArticlesEntities(Topic.Headlines).toArticles()
//    }
}