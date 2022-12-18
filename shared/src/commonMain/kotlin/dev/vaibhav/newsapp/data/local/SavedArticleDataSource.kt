package dev.vaibhav.newsapp.data.local

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import database.ArticleEntity
import dev.vaibhav.newsapp.database.NewsDatabase

class SavedArticleDataSource(database: NewsDatabase) {

    private val queries = database.savedArticleQueries

    val savedArticles = queries.getAllSavedArticles().asFlow().mapToList()

    suspend fun getSavedArticles() = queries.getAllSavedArticles().executeAsList()

    suspend fun saveArticle(article:ArticleEntity) = queries.insertArticle(
        id = null,
        author = article.author,
        content = article.content,
        description = article.description,
        published_at = article.published_at,
        source = article.source,
        title = article.title,
        url = article.url,
        image_url = article.image_url,
        topic = article.topic
    )

    suspend fun unSaveArticle(id:Long) = queries.removeArticle(id)
}