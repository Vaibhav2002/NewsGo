package dev.vaibhav.newsapp.data.remote.dataSource.news

import dev.vaibhav.newsapp.data.models.remote.NewsResponse
import dev.vaibhav.newsapp.data.remote.dataSource.RemoteDataSource

class NewsRemoteDataSource : RemoteDataSource() {

    companion object {
        private const val TOP_HEADLINE = "top-headlines"
        private const val EVERYTHING = "everything"
    }

    suspend fun getTopHeadlines(
        country: String
    ) = get<NewsResponse>(TOP_HEADLINE) {
        url { parameters.append("country", country) }
    }.articles

    suspend fun getNewsByQuery(
        query: String
    ) = get<NewsResponse>(EVERYTHING) {
        url { parameters.append("q", query) }
    }.articles
}