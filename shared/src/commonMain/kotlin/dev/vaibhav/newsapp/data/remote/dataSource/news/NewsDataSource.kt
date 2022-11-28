package dev.vaibhav.newsapp.data.remote.dataSource.news

import dev.vaibhav.newsapp.data.models.NewsResponse
import dev.vaibhav.newsapp.data.remote.dataSource.BaseDataSource
import dev.vaibhav.newsapp.domain.Topic

class NewsDataSource : BaseDataSource() {

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