@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)

package dev.vaibhav.newsapp.android.presentation.screens.search

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.vaibhav.newsapp.android.presentation.components.AppBar
import dev.vaibhav.newsapp.android.presentation.components.articlesList
import dev.vaibhav.newsapp.android.presentation.components.emptyStates.NoResults
import dev.vaibhav.newsapp.domain.models.Article
import dev.vaibhav.newsapp.presentation.search.SearchScreenState

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    navigateToDetail: (Article) -> Unit,
    onBackPress: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { SearchScreenAppBar(scrollBehavior, onBackPress) },
    ) {
        SearchScreenContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            viewModel = viewModel,
            navigateToDetail = navigateToDetail
        )
    }
}

@Composable
private fun SearchScreenAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    onBackPress: () -> Unit
) {
    AppBar(
        title = "Search",
        scrollBehavior = scrollBehavior,
        isBackEnabled = true,
        onBackPress = onBackPress
    )
}

@Composable
private fun SearchScreenContent(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel,
    navigateToDetail: (Article) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    Column(modifier = modifier) {
        SearchBox(
            query = uiState.searchQuery,
            onQueryChanged = viewModel::onSearchQueryChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            AnimatedContent(
                modifier = Modifier,
                contentAlignment = Alignment.Center,
                targetState = uiState.articles.isNotEmpty()
            ) {
                if (it)
                    SearchScreenSuccessContent(
                        viewModel = viewModel,
                        uiState = uiState,
                        modifier = Modifier.fillMaxSize(),
                        navigateToDetail = navigateToDetail
                    )
                else
                    NoResults(message = "Search something else")
            }
            if (uiState.isLoading) ProgressBar()
        }
    }

}

@Composable
private fun SearchScreenSuccessContent(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel,
    uiState: SearchScreenState,
    navigateToDetail: (Article) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        articlesList(
            articles = uiState.articles,
            onArticleClick = navigateToDetail,
            onArticleSaveClick = viewModel::onSaveToggled
        )
    }
}

@Composable
private fun ProgressBar(
    modifier: Modifier = Modifier
) {
    CircularProgressIndicator(
        modifier = modifier
    )
}