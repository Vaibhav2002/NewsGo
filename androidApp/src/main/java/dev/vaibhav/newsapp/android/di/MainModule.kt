package dev.vaibhav.newsapp.android.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.vaibhav.newsapp.data.local.ArticleLocalDataSource
import dev.vaibhav.newsapp.data.local.DatabaseDriverFactory
import dev.vaibhav.newsapp.data.local.SavedArticleDataSource
import dev.vaibhav.newsapp.data.remote.dataSource.news.NewsRemoteDataSource
import dev.vaibhav.newsapp.data.repo.NewsRepoImpl
import dev.vaibhav.newsapp.data.repo.SavedNewsRepoImpl
import dev.vaibhav.newsapp.database.NewsDatabase
import dev.vaibhav.newsapp.domain.repo.NewsRepo
import dev.vaibhav.newsapp.domain.repo.SavedNewsRepo
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    fun providesNewsDataSource(): NewsRemoteDataSource = NewsRemoteDataSource()


    @Provides
    @Singleton
    fun providesDatabaseFactory(
        @ApplicationContext context: Context
    ): DatabaseDriverFactory = DatabaseDriverFactory(context)

    @Provides
    @Singleton
    fun providesNewsDatabase(
        factory: DatabaseDriverFactory
    ):NewsDatabase = NewsDatabase(
        driver = factory.createDriver()
    )

    @Provides
    @Singleton
    fun providesLocalArticleDataSource(
        database:NewsDatabase
    ): ArticleLocalDataSource = ArticleLocalDataSource(database)

    @Provides
    @Singleton
    fun providesSavedArticleDataSource(
        database:NewsDatabase
    ): SavedArticleDataSource = SavedArticleDataSource(database)

    @Provides
    @Singleton
    fun providesSavedNewsRepo(
        dataSource: SavedArticleDataSource
    ):SavedNewsRepo = SavedNewsRepoImpl(dataSource)

    @Provides
    @Singleton
    fun providesNewsRepo(
        dataSource: NewsRemoteDataSource,
        localDataSource: ArticleLocalDataSource,
        savedNewsRepo: SavedNewsRepo
    ): NewsRepo = NewsRepoImpl(dataSource, localDataSource, savedNewsRepo)
}