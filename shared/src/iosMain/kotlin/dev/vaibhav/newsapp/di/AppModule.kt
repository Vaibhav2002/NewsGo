package dev.vaibhav.newsapp.di

import database.SavedArticle
import dev.vaibhav.newsapp.data.local.ArticleLocalDataSource
import dev.vaibhav.newsapp.data.local.DatabaseDriverFactory
import dev.vaibhav.newsapp.data.local.SavedArticleDataSource
import dev.vaibhav.newsapp.data.remote.dataSource.news.NewsRemoteDataSource
import dev.vaibhav.newsapp.data.repo.NewsRepoImpl
import dev.vaibhav.newsapp.data.repo.SavedNewsRepoImpl
import dev.vaibhav.newsapp.database.NewsDatabase

class AppModule {

    private fun providesDatabase() = NewsDatabase(
        driver = DatabaseDriverFactory().createDriver(),
    )

    val articleRepo by lazy {
        NewsRepoImpl(
            dataSource = NewsRemoteDataSource(),
            localDataSource = ArticleLocalDataSource(providesDatabase()),
            savedArticleRepo = savedNewsRepo
        )
    }

    val savedNewsRepo by lazy {
        SavedNewsRepoImpl(SavedArticleDataSource(providesDatabase()))
    }
}