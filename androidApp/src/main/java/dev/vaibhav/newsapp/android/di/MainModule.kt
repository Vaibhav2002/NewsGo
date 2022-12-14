package dev.vaibhav.newsapp.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.vaibhav.newsapp.data.remote.dataSource.news.NewsRemoteDataSource
import dev.vaibhav.newsapp.data.repo.NewsRepoImpl
import dev.vaibhav.newsapp.domain.repo.NewsRepo

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    fun providesNewsDataSource():NewsRemoteDataSource = NewsRemoteDataSource()

    @Provides
    fun providesNewsRepo(dataSource: NewsRemoteDataSource):NewsRepo = NewsRepoImpl(dataSource)
}