package dev.vaibhav.newsapp.android.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.vaibhav.newsapp.android.presentation.screens.detail.ArticleScreen
import dev.vaibhav.newsapp.android.presentation.screens.home.HomeScreen
import dev.vaibhav.newsapp.android.presentation.screens.navigation.MainNavHost
import dev.vaibhav.newsapp.android.presentation.theme.NewsAppTheme
import dev.vaibhav.newsapp.domain.models.Article

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val navController = rememberNavController()
                    MainNavHost(navController = navController, modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}