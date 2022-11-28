package dev.vaibhav.newsapp.data.remote.dataSource.news

import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class NewsDataSourceTest {

    private lateinit var dataSource: NewsDataSource

    @BeforeTest
    fun setUp() {
        dataSource = NewsDataSource()
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