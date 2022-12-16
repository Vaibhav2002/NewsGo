package dev.vaibhav.newsapp.android.presentation.screens.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.vaibhav.newsapp.android.presentation.components.AppBar
import dev.vaibhav.newsapp.android.presentation.components.NewsItem
import dev.vaibhav.newsapp.domain.Topic
import dev.vaibhav.newsapp.domain.models.Article
import dev.vaibhav.newsapp.presentation.home.HomeScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail:(Article)->Unit
) {
    val state by viewModel.uiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { AppBar(title = state.topic.topic, scrollBehavior = scrollBehavior) },
    ) {
        HomeScreenContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            viewModel = viewModel,
            state = state,
            onArticleClick = navigateToDetail
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    state: HomeScreenState,
    onArticleClick:(Article)->Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            TopicChips(
                topics = state.topics,
                selectedTopic = state.topic,
                onTopicChanged = viewModel::onTopicChange,
                modifier = Modifier.fillMaxWidth()
            )
        }
        items(state.articles, key = Article::title) {
            NewsItem(
                article = it,
                onClick = onArticleClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .animateItemPlacement(tween(durationMillis = 500))
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopicChips(
    modifier: Modifier = Modifier,
    topics: List<Topic>,
    selectedTopic: Topic,
    onTopicChanged: (Topic) -> Unit
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(topics) {
            ElevatedFilterChip(
                shape = RoundedCornerShape(8.dp),
                selected = selectedTopic == it,
                label = { Text(text = it.topic, style = MaterialTheme.typography.labelLarge, modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp)) },
                onClick = { onTopicChanged(it) }
            )
        }
    }
}