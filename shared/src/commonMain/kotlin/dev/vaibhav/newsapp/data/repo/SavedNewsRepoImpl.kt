package dev.vaibhav.newsapp.data.repo

import database.ArticleEntity
import dev.vaibhav.newsapp.data.local.SavedArticleDataSource
import dev.vaibhav.newsapp.domain.mappers.toArticle
import dev.vaibhav.newsapp.domain.mappers.toArticleEntity
import dev.vaibhav.newsapp.domain.models.Article
import dev.vaibhav.newsapp.domain.models.Saved
import dev.vaibhav.newsapp.domain.repo.SavedNewsRepo
import kotlinx.coroutines.flow.map

class SavedNewsRepoImpl(private val dataSource: SavedArticleDataSource) : SavedNewsRepo {

    override val savedArticle = dataSource.savedArticles.map {
        it.map { it.toArticle() }
    }

    override suspend fun getSavedArticles() = dataSource.getSavedArticles().map { it.toArticle() }

    override suspend fun saveArticle(article: Article) =
        dataSource.saveArticle(article.toArticleEntity())

    override suspend fun unSaveArticle(article: Article) {
        if(article.saved == null) return
        dataSource.unSaveArticle(article.url)
    }

    override suspend fun isSaved(article: ArticleEntity): Boolean {
        return getSavedArticles().find { it.url == article.url } != null
    }

    override suspend fun getSaved(article: ArticleEntity) =
        getSavedArticles().find { it.url == article.url }?.saved?.let {
            Saved(true, it.timeStamp)
        }
}