package dev.vaibhav.newsapp.di

import dev.vaibhav.newsapp.data.local.ArticleLocalDataSource
import dev.vaibhav.newsapp.data.local.DatabaseDriverFactory
import dev.vaibhav.newsapp.data.remote.dataSource.news.NewsRemoteDataSource
import dev.vaibhav.newsapp.data.repo.NewsRepoImpl
import dev.vaibhav.newsapp.database.NewsDatabase

class AppModule {

    private fun providesDatabase() = NewsDatabase(DatabaseDriverFactory().createDriver())

    val articleRepo by lazy {
        NewsRepoImpl(
            dataSource = NewsRemoteDataSource(),
            localDataSource = ArticleLocalDataSource(providesDatabase())
        )
    }
}