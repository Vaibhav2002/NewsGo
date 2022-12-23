package dev.vaibhav.newsapp.domain.repo

import database.ArticleEntity
import dev.vaibhav.newsapp.domain.models.Article
import dev.vaibhav.newsapp.domain.models.Saved
import kotlinx.coroutines.flow.Flow

interface SavedNewsRepo {

    val savedArticle: Flow<List<Article>>

    suspend fun getSavedArticles(): List<Article>

    suspend fun saveArticle(article: Article)

    suspend fun unSaveArticle(article: Article)

    suspend fun isSaved(article: ArticleEntity): Boolean

    suspend fun getSaved(article: ArticleEntity): Saved?
}