package dev.vaibhav.newsapp.android.presentation.screens.navigation

import android.net.Uri
import dev.vaibhav.newsapp.android.domain.util.encodeUrl
import dev.vaibhav.newsapp.domain.models.Article
import dev.vaibhav.newsapp.utils.serialize.JsonSerializer
import dev.vaibhav.newsapp.utils.serialize.Serializer
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class Screens(val route: String) {
    object Home : Screens("homeScreen")

    object ArticleDetail : Screens("articleDetail/{article}") {
        const val articleArg = "article"

        fun createRoute(article: Article): String {
            return article.copy(
                urlToImage = article.urlToImage.encodeUrl(),
                url = article.url.encodeUrl()
            ).let {
                route.replace("{$articleArg}", JsonSerializer.serialize(it))
            }
        }

    }

    object SavedArticles : Screens("savedArticles")

    object Search : Screens("searchScreen")
}
