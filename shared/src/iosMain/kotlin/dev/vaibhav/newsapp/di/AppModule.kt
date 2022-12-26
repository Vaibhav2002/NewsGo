package dev.vaibhav.newsapp.di

import dev.vaibhav.newsapp.data.local.ArticleLocalDataSource
import dev.vaibhav.newsapp.data.local.DatabaseDriverFactory
import dev.vaibhav.newsapp.data.local.SavedArticleDataSource
import dev.vaibhav.newsapp.data.remote.dataSource.news.NewsRemoteDataSource
import dev.vaibhav.newsapp.data.repo.NewsRepoImpl
import dev.vaibhav.newsapp.data.repo.SavedNewsRepoImpl
import dev.vaibhav.newsapp.database.NewsDatabase
import dev.vaibhav.newsapp.domain.usecases.SaveArticleUseCase

object AppModule {

    private fun providesDatabase() = NewsDatabase(
        driver = DatabaseDriverFactory().createDriver(),
    )

    private fun providesSavedArticleDataSource() = SavedArticleDataSource(
        providesDatabase()
    )

    val articleRepo by lazy {
        NewsRepoImpl(
            dataSource = NewsRemoteDataSource(),
            localDataSource = ArticleLocalDataSource(providesDatabase()),
            savedArticleRepo = savedNewsRepo
        )
    }

    val savedNewsRepo by lazy {
        SavedNewsRepoImpl(providesSavedArticleDataSource())
    }

    val saveArticleUseCase by lazy {
        SaveArticleUseCase(savedNewsRepo)
    }
}