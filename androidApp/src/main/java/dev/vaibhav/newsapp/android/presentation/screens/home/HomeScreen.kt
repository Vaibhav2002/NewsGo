@file:OptIn(ExperimentalMaterialApi::class)

package dev.vaibhav.newsapp.android.presentation.screens.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.vaibhav.newsapp.android.presentation.components.AppBar
import dev.vaibhav.newsapp.android.presentation.components.articlesList
import dev.vaibhav.newsapp.android.presentation.components.emptyStates.NoResults
import dev.vaibhav.newsapp.android.presentation.components.topicChips
import dev.vaibhav.newsapp.domain.Topic
import dev.vaibhav.newsapp.domain.models.Article
import dev.vaibhav.newsapp.presentation.home.HomeScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (Article) -> Unit,
    navigateToSavedScreen: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            HomeAppBar(
                scrollBehavior = scrollBehavior,
                topic = state.topic,
                navigateToSavedScreen
            )
        },
    ) {
        HomeScreenContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            viewModel = viewModel,
            state = state,
            navigateToDetail = navigateToDetail
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    topic: Topic,
    navigateToSavedScreen: () -> Unit
) {
    AppBar(
        title = topic.topic,
        scrollBehavior = scrollBehavior,
        actions = {
            FilledIconButton(
                onClick = navigateToSavedScreen,
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp),
                    contentColor = MaterialTheme.colorScheme.secondary
                ),
                modifier = Modifier.clip(CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Saved Screen"
                )
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    state: HomeScreenState,
    navigateToDetail: (Article) -> Unit
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isRefreshing,
        onRefresh = viewModel::onRefresh
    )
    AnimatedContent(
        targetState = state.articles.isEmpty(),
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState)
        ) {
            if (it)
                NoResults(modifier = Modifier.align(Alignment.Center))
            else
                HomeScreenSuccessContent(
                    modifier = Modifier.fillMaxSize(),
                    state = state,
                    viewModel = viewModel,
                    navigateToDetail = navigateToDetail
                )
            PullRefreshIndicator(
                refreshing = state.isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter),
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
private fun HomeScreenSuccessContent(
    modifier: Modifier = Modifier,
    state: HomeScreenState,
    viewModel: HomeViewModel,
    navigateToDetail: (Article) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            LazyRow(
                modifier = modifier,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                topicChips(
                    topics = state.topics,
                    selectedTopic = state.topic,
                    onTopicChanged = viewModel::onTopicChange,
                )
            }
        }
        articlesList(
            articles = state.articles,
            onArticleClick = navigateToDetail,
            onArticleSaveClick = viewModel::onSaveClick
        )
    }
}

