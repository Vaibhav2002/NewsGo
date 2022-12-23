package dev.vaibhav.newsapp.data.local

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import database.ArticleEntity
import dev.vaibhav.newsapp.database.NewsDatabase

class ArticleLocalDataSource(database: NewsDatabase) {

    private val queries = database.articleQueries

    val articles = queries.getAllArticles().asFlow().mapToList()

    suspend fun insertArticles(articles: List<ArticleEntity>) = articles.forEach {
        queries.insertArticle(
            author = it.author,
            content = it.content,
            description = it.description,
            published_at = it.published_at,
            source = it.source,
            title = it.title,
            url = it.url,
            image_url = it.image_url,
            topic = it.topic
        )
    }

    suspend fun getArticleByUrl(url: String) = queries.getArticleByUrl(url).executeAsOne()

//    suspend fun deleteArticleByUrl(id: Long) = queries.deleteArticleById(id)

    suspend fun deleteArticleByTopic(topic: String) = queries.deleteArticlesByType(topic)

    suspend fun deleteAll() = queries.deleteAll()

}