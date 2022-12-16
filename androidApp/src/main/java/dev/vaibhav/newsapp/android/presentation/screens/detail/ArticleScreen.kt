package dev.vaibhav.newsapp.android.presentation.screens.detail

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.core.text.buildSpannedString
import androidx.core.text.color
import dev.vaibhav.newsapp.android.presentation.components.AppBar
import dev.vaibhav.newsapp.android.presentation.components.NewsItem
import dev.vaibhav.newsapp.domain.models.Article

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ArticleScreen(
    modifier: Modifier = Modifier,
    article: Article,
) {
    val scrollState = rememberScrollState()
    Scaffold(
        modifier = modifier,
        topBar = {
            ArticleCover(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(4f / 3),
                image = article.urlToImage,
                title = article.title
            )
        }
    ) {
        ArticleDetailContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            article = article,
            scrollState = scrollState
        )
    }
}

@Composable
fun ArticleDetailContent(
    modifier: Modifier = Modifier,
    article: Article,
    scrollState: ScrollState
) {
    val uriHandler = LocalUriHandler.current
    val content = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.onBackground,
            )
        ){
            append(article.content.substringBeforeLast("["))
        }

        pushStringAnnotation("Read More", annotation = "Read More")
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.tertiary,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )
        ){
            append("Read More")
        }
    }
    Column(
        modifier = modifier.padding(16.dp).verticalScroll(scrollState),
    ) {
        Text(
            text = article.description,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        ClickableText(
            text = content,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .alpha(0.8f),
        ){
            content.getStringAnnotations("Read More", it, it)
                .firstOrNull()?.also {
                    uriHandler.openUri(article.url)
                }
        }
    }
}