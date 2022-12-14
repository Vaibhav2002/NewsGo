package dev.vaibhav.newsapp.data.remote.dataSource.news

import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class NewsDataSourceTest {

    private lateinit var dataSource: NewsRemoteDataSource

    @BeforeTest
    fun setUp() {
        dataSource = NewsRemoteDataSource()
    }

    @Test
    fun `fetches all top headlines For India`() = runTest {
        val headlines = dataSource.getTopHeadlines("in")
        assertTrue(headlines.isNotEmpty(), "Check data is not empty")
        assertTrue(
            headlines.any {
                it.title!!.contains("India") || it.description!!.contains("India")
            },
            "Check news of given Country"
        )
    }
}