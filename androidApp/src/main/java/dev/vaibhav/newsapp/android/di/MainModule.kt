package dev.vaibhav.newsapp.android.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.vaibhav.newsapp.data.local.ArticleLocalDataSource
import dev.vaibhav.newsapp.data.local.DatabaseDriverFactory
import dev.vaibhav.newsapp.data.remote.dataSource.news.NewsRemoteDataSource
import dev.vaibhav.newsapp.data.repo.NewsRepoImpl
import dev.vaibhav.newsapp.database.NewsDatabase
import dev.vaibhav.newsapp.domain.repo.NewsRepo

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    fun providesNewsDataSource():NewsRemoteDataSource = NewsRemoteDataSource()

    @Provides
    fun providesDatabaseFactory(
        @ApplicationContext context:Context
    ):DatabaseDriverFactory = DatabaseDriverFactory(context)

    @Provides
    fun providesLocalArticleDataSource(
        factory: DatabaseDriverFactory
    ):ArticleLocalDataSource = ArticleLocalDataSource(NewsDatabase.invoke(factory.createDriver()))

    @Provides
    fun providesNewsRepo(
        dataSource: NewsRemoteDataSource,
        localDataSource: ArticleLocalDataSource
    ):NewsRepo = NewsRepoImpl(dataSource, localDataSource)
}