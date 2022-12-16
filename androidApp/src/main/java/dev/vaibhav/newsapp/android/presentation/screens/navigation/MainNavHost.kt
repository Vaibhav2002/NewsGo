package dev.vaibhav.newsapp.android.presentation.screens.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.vaibhav.newsapp.android.presentation.screens.detail.ArticleScreen
import dev.vaibhav.newsapp.android.presentation.screens.home.HomeScreen
import dev.vaibhav.newsapp.android.presentation.screens.navigation.Screens.ArticleDetail
import dev.vaibhav.newsapp.android.presentation.screens.navigation.Screens.Home
import dev.vaibhav.newsapp.domain.models.Article
import dev.vaibhav.newsapp.utils.serialize.JsonSerializer

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Home.route,
        modifier = modifier
    ) {
        composable(Home.route) {
            HomeScreen(Modifier.fillMaxSize()) {
                navController.navigate(ArticleDetail.createRoute(it))
            }
        }
        composable(
            "articleDetail/{article}",
            arguments = listOf(navArgument("article") { type = NavType.StringType })
        ) {
            val article = it.arguments?.getString("article")?.let {
                JsonSerializer.deserialize<Article>(it)
            } ?: return@composable
            ArticleScreen(article = article, modifier = Modifier.fillMaxSize())
        }
    }
}