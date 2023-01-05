package dev.vaibhav.newsapp.data.remote.dataSource.news

import dev.vaibhav.newsapp.data.models.remote.NewsResponse
import dev.vaibhav.newsapp.data.remote.dataSource.RemoteDataSource
import dev.vaibhav.newsapp.domain.models.Topic

class NewsRemoteDataSource : RemoteDataSource() {

    companion object {
        private const val TOP_HEADLINE = "top-headlines"
        private const val EVERYTHING = "everything"
    }

    suspend fun getTopHeadlines(
        country: String,
        category:String,
    ) = get<NewsResponse>(TOP_HEADLINE) {
        url {
            parameters.append("country", country)
            if(category != Topic.Headlines.topic)
                parameters.append("category", category)
        }
    }.articles

    suspend fun getNewsByQuery(
        query: String
    ) = get<NewsResponse>(EVERYTHING) {
        url { parameters.append("q", query) }
    }.articles
}