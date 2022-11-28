package dev.vaibhav.newsapp.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.vaibhav.newsapp.data.remote.dataSource.news.NewsDataSource
import dev.vaibhav.newsapp.data.remote.repo.news.NewsRepoImpl
import dev.vaibhav.newsapp.domain.repo.NewsRepo

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    fun providesNewsDataSource():NewsDataSource = NewsDataSource()

    @Provides
    fun providesNewsRepo(dataSource: NewsDataSource):NewsRepo = NewsRepoImpl(dataSource)
}