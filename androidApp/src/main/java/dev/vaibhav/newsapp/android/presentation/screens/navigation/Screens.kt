package dev.vaibhav.newsapp.android.presentation.screens.navigation

import android.net.Uri
import com.google.gson.Gson
import dev.vaibhav.newsapp.domain.models.Article

sealed class Screens(val route: String) {
    object Home : Screens("homeScreen")

    object ArticleDetail : Screens("articleDetail/{article}") {
        const val articleArg = "article"

        fun createRoute(article: Article) = Gson().toJson(article).let {
            route.replace("{article}", it)
        }
    }
}
