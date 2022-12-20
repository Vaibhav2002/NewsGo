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
import dev.vaibhav.newsapp.android.presentation.screens.navigation.Screens.SavedArticles
import dev.vaibhav.newsapp.android.presentation.screens.saved.SavedArticlesScreen
import dev.vaibhav.newsapp.domain.models.Article

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navigateBack:()->Unit = { navController.popBackStack() }
    val navigateToDetail:(Article) -> Unit = {
        navController.navigate(ArticleDetail.createRoute(it))
    }
    NavHost(
        navController = navController,
        startDestination = Home.route,
        modifier = modifier
    ) {
        composable(Home.route) {
            HomeScreen(
                modifier = Modifier.fillMaxSize(),
                navigateToDetail = navigateToDetail,
                navigateToSavedScreen = {
                    navController.navigate(SavedArticles.route)
                }
            )
        }
        composable(
            ArticleDetail.route,
            arguments = listOf(navArgument(ArticleDetail.articleArg) { type = NavType.StringType })
        ) {
            ArticleScreen(
                modifier = Modifier.fillMaxSize(),
                onBack = navigateBack
            )
        }

        composable(SavedArticles.route){
            SavedArticlesScreen(
                modifier = Modifier.fillMaxSize(),
                onBackPress = navigateBack,
                navigateToDetailScreen = navigateToDetail
            )
        }
    }
}