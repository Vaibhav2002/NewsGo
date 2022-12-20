package dev.vaibhav.newsapp.android.presentation.screens.saved

import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.vaibhav.newsapp.android.presentation.components.AppBar
import dev.vaibhav.newsapp.android.presentation.components.NewsItem
import dev.vaibhav.newsapp.domain.models.Article

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedArticlesScreen(
    modifier:Modifier = Modifier,
    viewModel: SavedArticlesViewModel = hiltViewModel(),
    onBackPress:()->Unit,
    navigateToDetailScreen:(Article)->Unit
) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { SavedArticlesAppBar(scrollBehavior, onBackPress) }
    ) {
        SavedArticleScreenContent(
            modifier = Modifier.fillMaxSize().padding(it),
            viewModel = viewModel,
            navigateToDetailScreen = navigateToDetailScreen
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SavedArticlesAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    onBackPress: () -> Unit
) {
    AppBar(
        title = "Saved Articles",
        scrollBehavior = scrollBehavior,
        isBackEnabled = true,
        onBackPress = onBackPress
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SavedArticleScreenContent(
    modifier:Modifier = Modifier,
    viewModel:SavedArticlesViewModel,
    navigateToDetailScreen: (Article) -> Unit
){
    val uiState by viewModel.uiState.collectAsState()
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ){
        items(uiState.articles, key = Article::url){
            NewsItem(
                article = it,
                onClick = navigateToDetailScreen,
                onSaveClick = viewModel::onSaveToggled,
                modifier = Modifier
                    .fillMaxWidth()
                    .animateItemPlacement(tween(durationMillis = 500))
            )
        }
    }
}