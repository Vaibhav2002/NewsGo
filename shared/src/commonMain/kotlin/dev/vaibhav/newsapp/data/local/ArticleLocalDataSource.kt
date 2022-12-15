package dev.vaibhav.newsapp.data.local

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import database.ArticleEntity
import dev.vaibhav.newsapp.database.NewsDatabase

class ArticleLocalDataSource(database: NewsDatabase) {

    private val queries = database.articleQueries

    val articles = queries.getAllArticles().asFlow().mapToList()

    suspend fun insertArticles(articles:List<ArticleEntity>) = articles.map(queries::insertArticle)

    suspend fun getArticleById(id:Long) = queries.getArticleById(id).executeAsOne()

    suspend fun deleteArticleById(id: Long) = queries.deleteArticleById(id)

    suspend fun deleteArticleByTopic(topic:String) = queries.deleteArticlesByType(topic)

    suspend fun deleteAll() = queries.deleteAll()


}