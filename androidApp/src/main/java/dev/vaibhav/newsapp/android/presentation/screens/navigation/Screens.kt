package dev.vaibhav.newsapp.android.presentation.screens.navigation

import android.net.Uri
import com.google.gson.Gson
import dev.vaibhav.newsapp.android.presentation.screens.navigation.Screens.ArticleDetail.articleArg
import dev.vaibhav.newsapp.domain.models.Article

sealed class Screens(val route: String) {
    object Home : Screens("homeScreen")

    object ArticleDetail : Screens("articleDetail/{articleId}") {
        const val articleArg = "articleId"

        fun createRoute(article: Article) = route.replace("{$articleArg}", article.id.toString())

    }
}
