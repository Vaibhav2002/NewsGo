package dev.vaibhav.newsapp.android.presentation.screens.navigation

import dev.vaibhav.newsapp.domain.models.Article
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class Screens(val route: String) {
    object Home : Screens("homeScreen")

    object ArticleDetail : Screens("articleDetail/{articleUrl}") {
        const val articleArg = "articleUrl"

        fun createRoute(article: Article): String {
            val url = URLEncoder.encode(article.url, StandardCharsets.UTF_8.toString())
            return route.replace("{$articleArg}", url)
        }

    }

    object SavedArticles : Screens("savedArticles")
}
