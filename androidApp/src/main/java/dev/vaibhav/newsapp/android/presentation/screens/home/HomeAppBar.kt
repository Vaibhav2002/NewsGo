package dev.vaibhav.newsapp.android.presentation.screens.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import dev.vaibhav.newsapp.android.presentation.components.AppBar
import dev.vaibhav.newsapp.android.presentation.components.AppBarButton
import dev.vaibhav.newsapp.domain.models.Topic

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    topic: Topic,
    navigateToSearchScreen:()->Unit,
    navigateToSavedScreen: () -> Unit
) {
    AppBar(
        title = topic.topic,
        scrollBehavior = scrollBehavior,
        buttons = listOf(
            AppBarButton(
                icon = Icons.Rounded.Search,
                contentDescription = "Search News",
                onClick = navigateToSearchScreen
            ),
            AppBarButton(
                icon = Icons.Rounded.FavoriteBorder,
                contentDescription = "Saved Articles",
                onClick = navigateToSavedScreen
            )
        )
    )
}